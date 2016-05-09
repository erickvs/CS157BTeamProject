package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import engine.BIToolAction;
import engine.CustomJButton;
import engine.CustomJComboBox;
import engine.CustomJLabel;
import engine.QueryEngine;
import engine.StateModel;

public class BISystemUI {
	
	/*****************       Engine       *****************/
	private static StateModel sm = new StateModel();
	private static QueryEngine qe = new QueryEngine(sm);
	/******************************************************/
	
	
	/*********************   JDBC    **********************/
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Groceries";
	static final String USER = "root";
	static final String PASS = "pa$$w0rd"; // not my actual password
	/******************************************************/
	
	/*********************   FRAME   **********************/
	private static JFrame frame;
	private static int frameWidth = 1300; 	// 1300
	private static int frameHeight = 600; 	// 600 --> 	If you change this then you have to play 
											//         	with the field 'ipady' for a while to get 
											//         	the borders to match, otherwise it will look
											// 		   	weird. Just do CTRL-F, or whatever, in here to
											//			find that field.
	/******************************************************/
	
	/******************* STATE PANEL **********************/
	private static JPanel statePanel;
	/******************************************************/
	
	/*************** LEFT AND RIGHT PANELS ****************/
	private static JPanel leftPanel;
	private static JPanel rightPanel;
	private static int panelWidth  =  
			(int)(490.0 / 1000 * frameWidth) ; // DO NOT MODIFY
	private static int panelHeight =  
			(int)(465.0 / 500  * frameHeight) ; // DO NOT MODIFY
	/******************************************************/
	
	/************** RIGHT PANEL COMPONENTS ****************/
	private static JTextArea display;
	private static JTextArea sqlDisplay;
	private static JScrollPane scroll;
	private static JScrollPane sqlScroll;
	private static JButton resetButton;
	private static JPanel buttonPanel;
	/******************************************************/
	
	/*************** LEFT PANEL COMPONENTS ****************/
	// CONSTANTS
	private final static int CURRENT_STATE_FONT_SIZE = 17;
	private final static Color CURRENT_STATE_FONT_COLOR = new Color(50, 50, 50);
	
	// Tabbed Panes
	private static JTabbedPane rollUpTabbedPane;
	private static JTabbedPane drillDownTabbedPane;
	private static JTabbedPane sliceAndDiceTabbedPane;
	
	// Elements for "Roll Up - Dimension Reduction" tab
	private static JPanel rollUpByDimensionReductionPanel;
	private static JLabel rollUpDimReducLabel;
	private static CustomJComboBox rollUpDimReducComboBox;
	private static CustomJButton rollUpDimReducButton;
	
	// Elements for "Roll Up - Climbing Hierarchy" tab
	private static JPanel rollUpByClimbingHierarchyPanel;
	private static JLabel rollUpClimHierarLabel;
	private static CustomJComboBox  rollUpClimHierarComboBox;
	private static CustomJButton rollUpClimHierarButton;
	
	// Elements for "Drill Down - Add Dimension" tab
	private static JPanel drillDownAddDimensionPanel;
	private static JLabel drillDownAddDimLabel;
	private static CustomJComboBox drillDownAddDimComboBox;
	private static CustomJButton drillDownAdddimButton;
	
	// Elements for "Drill Down - Descend Hierarchy" tab
	private static JPanel drillDownDescendHierarchyPanel;
	private static JLabel drillDownDescHierarLabel;
	private static CustomJComboBox drillDownDescHierarComboBox;
	private static CustomJButton drillDownDescHierarButton;
	
	// Elements for "Dice"
	private static JPanel dicePanel;
	
	private static JLabel dicePickValueFromDimensionLabel1;
	private static JTextField dicePickValueFromDimensionJTextField1;
	private static JLabel dicePickValueFromDimensionLabel2;
	private static JTextField dicePickValueFromDimensionJTextField2;
	private static JLabel dicePickValueFromDimensionLabel3;
	private static JTextField dicePickValueFromDimensionJTextField3;
	private static JLabel dicePickValueFromDimensionLabel4;
	private static JTextField dicePickValueFromDimensionJTextField4;
	
	private static CustomJButton diceExecuteButton;
	
	// Elements for "Slice"
	private static JPanel slicePanel;
	private static JLabel sliceDimensionLabel;
	private static CustomJComboBox sliceDimensionComboBox;
	private static JLabel slicePickValueFromDimensionLabel;
	private static JTextField slicePickValueFromDimensionTextField; 
	private static CustomJButton sliceExecuteButton;
	/******************************************************/
	
	/****************STATE PANEL COMPONENTS****************/
	private static CustomJLabel timeLabel;
	private static CustomJLabel storeLabel;
	private static CustomJLabel promotionLabel;
	private static CustomJLabel productLabel;
	/******************************************************/
	
	public static void run() {		
		// Initialize JFrame.
		frame = new JFrame("Swayze's Business Intelligence Tool");
		frame.setSize(frameWidth, frameHeight);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/******************************************************************/
		// Initialize state panel and fill it with the state labels
		statePanel = new JPanel(new GridLayout(4,1));
		statePanel.setBorder(BorderFactory.createTitledBorder("Current Dimensions States"));
		
		timeLabel = new CustomJLabel(sm, "Time");
		timeLabel.setForeground(CURRENT_STATE_FONT_COLOR);
		timeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		sm.addView(timeLabel);
		
		storeLabel = new CustomJLabel(sm, "Store");
		storeLabel.setForeground(CURRENT_STATE_FONT_COLOR);
		storeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		sm.addView(storeLabel);
		
		promotionLabel = new CustomJLabel(sm, "Promotion");
		promotionLabel.setForeground(CURRENT_STATE_FONT_COLOR);
		promotionLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		sm.addView(promotionLabel);
		
		productLabel = new CustomJLabel(sm, "Product");
		productLabel.setForeground(CURRENT_STATE_FONT_COLOR);
		productLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		sm.addView(productLabel);
		
		statePanel.add(productLabel);
		statePanel.add(promotionLabel);
		statePanel.add(storeLabel);
		statePanel.add(timeLabel);
		
		/******************************************************************/
		
		// Initialize leftPanel
		leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setPreferredSize(new Dimension((int)(.8 * panelWidth), panelHeight));
		
		
		
		//
		//	ROLL UP BY DIMENTION REDUCTION TAB
		//
		rollUpByDimensionReductionPanel = new JPanel(new GridBagLayout());
		rollUpDimReducLabel = new JLabel("Dimension: ");
		rollUpDimReducComboBox = new CustomJComboBox(sm, BIToolAction.ROLLUP_DIM_REDUCTION); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR ROLLUP DIMREDUC
		sm.addView(rollUpDimReducComboBox); // Add this to the State model list of views.
		rollUpDimReducButton = new CustomJButton("Roll Up", sm, BIToolAction.ROLLUP_DIM_REDUCTION);  //<<<<<<<<<<<<<<<<<< ROLL UP -- DIMENSION REDUCTION BUTTON
		sm.addView(rollUpDimReducButton);
		rollUpDimReducButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dimension = (String) rollUpDimReducComboBox.getSelectedItem();
				display.setText(qe.createQuery(BIToolAction.ROLLUP_DIM_REDUCTION, dimension));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		rollUpByDimensionReductionPanel.add(rollUpDimReducLabel);
		rollUpByDimensionReductionPanel.add(rollUpDimReducComboBox);
		rollUpByDimensionReductionPanel.add(rollUpDimReducButton);
		
		//
		//	ROLL UP BY CLIMBING HIERARCHY TAB
		//
		rollUpByClimbingHierarchyPanel = new JPanel(new GridBagLayout());
		rollUpClimHierarLabel = new JLabel("Dimension: ");
		rollUpClimHierarComboBox = new CustomJComboBox(sm, BIToolAction.ROLLUP_CLIMB_HIERARCHY); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR ROLLUP CLIMB HIERARCHY
		sm.addView(rollUpClimHierarComboBox);
		rollUpClimHierarButton = new CustomJButton("Roll Up", sm, BIToolAction.ROLLUP_CLIMB_HIERARCHY); //<<<<<<<<<<<<<<<<<< ROLL UP -- CLIMBING HIERARCHY BUTTON
		sm.addView(rollUpClimHierarButton);
		rollUpClimHierarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dimension = (String) rollUpClimHierarComboBox.getSelectedItem();
				display.setText(qe.createQuery(BIToolAction.ROLLUP_CLIMB_HIERARCHY, dimension));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarLabel);
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarComboBox);
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarButton);
		
		//
		//	DRILL DOWN BY ADDING DIMENSION TAB
		//
		drillDownAddDimensionPanel =  new JPanel(new GridBagLayout());
		drillDownAddDimLabel = new JLabel("Dimension: ");
		drillDownAddDimComboBox = new CustomJComboBox(sm, BIToolAction.DRILLDOWN_ADD_DIM); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR DRILLDOWN ADDDIM
		sm.addView(drillDownAddDimComboBox);
		drillDownAdddimButton = new CustomJButton("Drill Down",sm, BIToolAction.DRILLDOWN_ADD_DIM); //<<<<<<<<<<<<<<<<<< DRILL DOWN -- ADDING DIMENSION BUTTON
		sm.addView(drillDownAdddimButton);
		drillDownAdddimButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dimension = (String) drillDownAddDimComboBox.getSelectedItem();
				display.setText(qe.createQuery(BIToolAction.DRILLDOWN_ADD_DIM, dimension));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		drillDownAddDimensionPanel.add(drillDownAddDimLabel);
		drillDownAddDimensionPanel.add(drillDownAddDimComboBox);
		drillDownAddDimensionPanel.add(drillDownAdddimButton);
		
		//
		//	DRILL DOWN BY DESCENDING HIERARCHY TAB
		//
		drillDownDescendHierarchyPanel = new JPanel(new GridBagLayout());
		drillDownDescHierarLabel = new JLabel("Dimension: ");
		drillDownDescHierarComboBox = new CustomJComboBox(sm, BIToolAction.DRILLDOWN_DESC_HIERARCHY); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR DRILLDOWN CLIMB HIER
		sm.addView(drillDownDescHierarComboBox);
		drillDownDescHierarButton = new CustomJButton("Drill Down", sm, BIToolAction.DRILLDOWN_DESC_HIERARCHY); //<<<<<<<<<<<<<<<<<< DRILL DOWN -- DESCEND HIERARCHY DIMENSION BUTTON
		sm.addView(drillDownDescHierarButton);
		drillDownDescHierarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dimension = (String) drillDownDescHierarComboBox.getSelectedItem();
				display.setText(qe.createQuery(BIToolAction.DRILLDOWN_DESC_HIERARCHY, dimension));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		drillDownDescendHierarchyPanel.add(drillDownDescHierarLabel);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarComboBox);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarButton);
		
		//
		//	DICE TAB
		//
		dicePanel = new JPanel(new GridBagLayout());
		dicePickValueFromDimensionLabel1 = new JLabel("Time:");
		dicePickValueFromDimensionJTextField1 = new JTextField(15);
		dicePickValueFromDimensionLabel2 = new JLabel("Store:");
		dicePickValueFromDimensionJTextField2 = new JTextField(15);
		dicePickValueFromDimensionLabel3 = new JLabel("Promotion:");
		dicePickValueFromDimensionJTextField3 = new JTextField(15);
		dicePickValueFromDimensionLabel4 = new JLabel("Product:");
		dicePickValueFromDimensionJTextField4 = new JTextField(15);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dicePickValueFromDimensionJTextField1.setText("");
				dicePickValueFromDimensionJTextField2.setText("");
				dicePickValueFromDimensionJTextField3.setText("");
				dicePickValueFromDimensionJTextField4.setText("");
			}
		});
		
		diceExecuteButton = new CustomJButton("Dice", sm, BIToolAction.DICE); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  DICE BUTTON
		diceExecuteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Map<String, String> map = new HashMap<>();
				String dimensionValue1 = dicePickValueFromDimensionJTextField1.getText();
				String dimensionValue2 = dicePickValueFromDimensionJTextField2.getText();
				String dimensionValue3 = dicePickValueFromDimensionJTextField3.getText();
				String dimensionValue4 = dicePickValueFromDimensionJTextField4.getText();
				
				if (!dimensionValue1.equals("")) map.put("Time", dimensionValue1);
				if (!dimensionValue2.equals("")) map.put("Store", dimensionValue2);
				if (!dimensionValue3.equals("")) map.put("Promotion", dimensionValue3);
				if (!dimensionValue4.equals("")) map.put("Product", dimensionValue4);
				
				display.setText(qe.dice(map));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		
		JPanel diceSubPanel = new JPanel(new GridLayout(4, 3));
		diceSubPanel.add(dicePickValueFromDimensionLabel1);
		diceSubPanel.add(dicePickValueFromDimensionJTextField1);
		diceSubPanel.add(dicePickValueFromDimensionLabel2);
		diceSubPanel.add(dicePickValueFromDimensionJTextField2);
		diceSubPanel.add(dicePickValueFromDimensionLabel3);
		diceSubPanel.add(dicePickValueFromDimensionJTextField3);
		diceSubPanel.add(dicePickValueFromDimensionLabel4);
		diceSubPanel.add(dicePickValueFromDimensionJTextField4);
		JPanel diceButtonSupanel = new JPanel(new BorderLayout());
		diceButtonSupanel.add(diceExecuteButton, BorderLayout.NORTH);
		diceButtonSupanel.add(clearBtn, BorderLayout.SOUTH);
		dicePanel.add(diceSubPanel);
		dicePanel.add(diceButtonSupanel);
		
		
		//
		//	SLICE TAB
		//
		slicePanel = new JPanel(new GridBagLayout());
		sliceDimensionLabel = new JLabel("Dim: ");
		sliceDimensionComboBox = new CustomJComboBox(sm, BIToolAction.SLICE);
		sm.addView(sliceDimensionComboBox);
		slicePickValueFromDimensionLabel = new JLabel("Value: ");
		slicePickValueFromDimensionTextField = new JTextField(15);
		sliceExecuteButton = new CustomJButton("Slice", sm, BIToolAction.SLICE); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  SLICE BUTTON
		sliceExecuteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dimension = (String) sliceDimensionComboBox.getSelectedItem();
				String dimensionValue = slicePickValueFromDimensionTextField.getText();
				display.setText(qe.slice(dimension, dimensionValue));
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		slicePanel.add(sliceDimensionLabel);
		slicePanel.add(sliceDimensionComboBox);
		slicePanel.add(slicePickValueFromDimensionLabel);
		slicePanel.add(slicePickValueFromDimensionTextField);
		slicePanel.add(sliceExecuteButton);
		
		// Create tabbed panes and add panels.
		rollUpTabbedPane = new JTabbedPane();
		rollUpTabbedPane.setBorder(BorderFactory.createTitledBorder("Roll Up"));
		rollUpTabbedPane.addTab("Dimension Reduction", rollUpByDimensionReductionPanel);
		rollUpTabbedPane.addTab("Climb Up Hierarchy", rollUpByClimbingHierarchyPanel);
		
		
		drillDownTabbedPane = new JTabbedPane();
		drillDownTabbedPane.setBorder(BorderFactory.createTitledBorder("Drill Down"));
		drillDownTabbedPane.add("Add Dimension", drillDownAddDimensionPanel);
		drillDownTabbedPane.addTab("Descend Hierarchy", drillDownDescendHierarchyPanel);
		
		sliceAndDiceTabbedPane = new JTabbedPane();
		sliceAndDiceTabbedPane.setBorder(BorderFactory.createTitledBorder("Dice and Slice"));
		sliceAndDiceTabbedPane.add("Dice", dicePanel);
		sliceAndDiceTabbedPane.add("Slice", slicePanel); //<<<<<<<<<<<<< ASK CLASSMATES
		
		
		// Add tabbed panes and the dice panel to the LEFT PANEL
		
		// The layout has to be, unfortunately, funky :\
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		leftPanel.add(rollUpTabbedPane, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		leftPanel.add(drillDownTabbedPane, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		leftPanel.add(sliceAndDiceTabbedPane, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		leftPanel.add(statePanel, c);
		/******************************************************************/
		// Initialize rightPanel
		rightPanel = new JPanel(new GridBagLayout());
		
		rightPanel.setPreferredSize(new Dimension((int) (1.2 * panelWidth), panelHeight));
		//rightPanel.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
		
		// Create the sql display
		sqlDisplay = new JTextArea();
		sqlDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
		sqlDisplay.setEditable(false);
		sqlDisplay.setText("Welcome to Code Swayze's BI Tool!");
		sqlScroll = new JScrollPane(sqlDisplay);
		sqlScroll.setBorder(BorderFactory.createTitledBorder("SQL"));
		qe.setSqlDisplay(sqlDisplay); //<<<<<< I KNOW ITS UGLY
		
		// Create the display (JTextArea)
		display = new JTextArea();
		display.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
		display.setEditable(false);
		display.setText("To start press 'Reset Central Cube'");
		scroll = new JScrollPane(display);
		scroll.setBorder(BorderFactory.createTitledBorder("Result"));
		
		// Reset to Central Cube Button
		resetButton = new JButton("Reset Central Cube"); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  RESET BUTTON
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				sqlDisplay.setText(qe.initialCentralCubeSQL());
				display.setText(qe.resetCube());
				sm.initialState();
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		
		
		// Add buttons to button panel
		buttonPanel = new JPanel(new GridLayout(1, 1));
		buttonPanel.setBorder(BorderFactory.createTitledBorder(""));
		buttonPanel.add(resetButton);
		/******************************************************************/
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		//c.gridwidth = 5;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		
		// Add  sql display to rightPanel
		rightPanel.add(sqlScroll, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 400;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		// Add display to rightPanel
		rightPanel.add(scroll, c);
		
		
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_END;
		// Add button panel to right panel
		rightPanel.add(buttonPanel, c);
		
		// Add leftPanel and rightPanel to frame.
		frame.add(leftPanel);
		frame.add(rightPanel);
		
		// And finally show the frame
		frame.setVisible(true);
		/******************************************************************/
	}
	
}
