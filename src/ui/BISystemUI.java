package ui;

import engine.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class BISystemUI {
	
	/*****************       Engine       *****************/
	private static QueryEngine qe = new QueryEngine();
	private static StateModel sm = new StateModel();
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
	private static JScrollPane scroll;
	private static JButton nextPage;
	private static JButton resetButton;
	private static JButton lastPage;
	private static JPanel buttonPanel;
	/******************************************************/
	
	/*************** LEFT PANEL COMPONENTS ****************/
	// CONSTANTS
	private final static int CURRENT_STATE_FONT_SIZE = 18;
	
	// Tabbed Panes
	private static JTabbedPane rollUpTabbedPane;
	private static JTabbedPane drillDownTabbedPane;
	private static JTabbedPane sliceAndDiceTabbedPane;
	
	// Elements for "Roll Up - Dimension Reduction" tab
	private static JPanel rollUpByDimensionReductionPanel;
	private static JLabel rollUpDimReducLabel;
	private static CustomJComboBox rollUpDimReducComboBox;
	private static JButton rollUpDimReducButton;
	
	// Elements for "Roll Up - Climbing Hierarchy" tab
	private static JPanel rollUpByClimbingHierarchyPanel;
	private static JLabel rollUpClimHierarLabel;
	private static CustomJComboBox  rollUpClimHierarComboBox;
	private static JButton rollUpClimHierarButton;
	
	// Elements for "Drill Down - Add Dimension" tab
	private static JPanel drillDownAddDimensionPanel;
	private static JLabel drillDownAddDimLabel;
	private static CustomJComboBox drillDownAddDimComboBox;
	private static JButton drillDownAdddimButton;
	
	// Elements for "Drill Down - Descend Hierarchy" tab
	private static JPanel drillDownDescendHierarchyPanel;
	private static JLabel drillDownDescHierarLabel;
	private static CustomJComboBox drillDownDescHierarComboBox;
	private static JButton drillDownDescHierarButton;
	
	// Elements for "Dice"
	private static JPanel dicePanel;
	private static JLabel dicePickDimensionLabel1;
	private static CustomJComboBox dicePickDimensionComboBox1;
	private static JLabel dicePickValueFromDimensionLabel1;
	private static CustomJComboBox dicePickValueFromDimensionComboBox1;
	private static JLabel dicePickDimensionLabel2;
	private static CustomJComboBox dicePickDimensionComboBox2;
	private static JLabel dicePickValueFromDimensionLabel2;
	private static CustomJComboBox dicePickValueFromDimensionComboBox2;
	
	private static JButton diceExecuteButton;
	
	// Elements for "Slice"
	private static JPanel slicePanel;
	private static JLabel sliceDimensionLabel;
	private static CustomJComboBox sliceDimensionComboBox;
	private static JLabel slicePickValueFromDimensionLabel;
	private static CustomJComboBox slicePickValueFromDimensionComboBox;
	private static JButton sliceExecuteButton;
	/******************************************************/
	
	/****************STATE PANEL COMPONENTS****************/
	private static JLabel timeLabel;
	private static JLabel storeLabel;
	private static JLabel promotionLabel;
	private static JLabel productLabel;
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
		statePanel.setBorder(BorderFactory.createTitledBorder("CURRENT STATE"));
		
		timeLabel = new JLabel("TIME: WEEK_NUMBER_OVERALL [MONTH] YEAR");
		timeLabel.setForeground(Color.BLUE);
		timeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		
		storeLabel = new JLabel("STORE: CITY [STORE_STATE] SALES_RIGION");
		storeLabel.setForeground(Color.BLUE);
		storeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		
		promotionLabel = new JLabel("PROMOTION:  [PROMOTION_NAME]");
		promotionLabel.setForeground(Color.BLUE);
		promotionLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		
		productLabel = new JLabel("PRODUCT: SUBCATEGORY [CATEGORY] DEPARTMENT");
		productLabel.setForeground(Color.BLUE);
		productLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, CURRENT_STATE_FONT_SIZE));
		
		statePanel.add(timeLabel);
		statePanel.add(storeLabel);
		statePanel.add(promotionLabel);
		statePanel.add(productLabel);
		/******************************************************************/
		
		// Initialize leftPanel
		leftPanel = new JPanel(new GridBagLayout());
		// leftPanel = new JPanel(new GridLayout(LEFT_PANEL_ROWS, LEFT_PANEL_COLUMNS));
		leftPanel.setPreferredSize(new Dimension((int)(.8 * panelWidth), panelHeight));
		
		
		
		//
		//	ROLL UP BY DIMENTION REDUCTION TAB
		//
		rollUpByDimensionReductionPanel = new JPanel(new GridBagLayout());
		rollUpDimReducLabel = new JLabel("Dimension: ");
		rollUpDimReducComboBox = new CustomJComboBox(sm, BIToolAction.ROLLUP_DIM_REDUCTION); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR ROLLUP DIMREDUC
		sm.addView(rollUpDimReducComboBox); // Add this to the State model list of views.
		rollUpDimReducButton = new JButton("ROLL UP");  //<<<<<<<<<<<<<<<<<< ROLL UP -- DIMENSION REDUCTION BUTTON
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
		rollUpClimHierarButton = new JButton("ROLL UP"); //<<<<<<<<<<<<<<<<<< ROLL UP -- CLIMBING HIERARCHY BUTTON
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
		drillDownAdddimButton = new JButton("DRILL DOWN"); //<<<<<<<<<<<<<<<<<< DRILL DOWN -- ADDING DIMENSION BUTTON
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
		drillDownDescHierarButton = new JButton("DRILL DOWN"); //<<<<<<<<<<<<<<<<<< DRILL DOWN -- DESCEND HIERARCHY DIMENSION BUTTON
		drillDownDescendHierarchyPanel.add(drillDownDescHierarLabel);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarComboBox);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarButton);
		
		//
		//	DICE TAB
		//
		dicePanel = new JPanel(new BorderLayout());
		
		JPanel diceSubPanelNorth = new JPanel();
		JPanel diceSubPanelSouth = new JPanel();
		JPanel diceButtonSupanel = new JPanel();
		diceButtonSupanel.setLayout(new FlowLayout());
		
		dicePickDimensionLabel1 = new JLabel("Dim1:");
		dicePickDimensionComboBox1 = new CustomJComboBox(sm, BIToolAction.DICE);
		sm.addView(dicePickDimensionComboBox1);
		dicePickValueFromDimensionLabel1 = new JLabel("Val:");
		dicePickValueFromDimensionComboBox1 = new CustomJComboBox(sm, BIToolAction.DICE);
		sm.addView(dicePickValueFromDimensionComboBox1);
		dicePickDimensionLabel2 = new JLabel("Dim2:");
		dicePickDimensionComboBox2 = new CustomJComboBox(sm, BIToolAction.DICE);
		sm.addView(dicePickDimensionComboBox2);
		dicePickValueFromDimensionLabel2 = new JLabel("Val:");
		dicePickValueFromDimensionComboBox2 = new CustomJComboBox(sm, BIToolAction.DICE);
		sm.addView(dicePickValueFromDimensionComboBox2);
		
		diceExecuteButton = new JButton("DICE"); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  DICE BUTTON
		diceSubPanelNorth.add(dicePickDimensionLabel1);
		diceSubPanelNorth.add(dicePickDimensionComboBox1);
		diceSubPanelNorth.add(dicePickValueFromDimensionLabel1);
		diceSubPanelNorth.add(dicePickValueFromDimensionComboBox1);
		diceSubPanelSouth.add(dicePickDimensionLabel2);
		diceSubPanelSouth.add(dicePickDimensionComboBox2);
		diceSubPanelSouth.add(dicePickValueFromDimensionLabel2);
		diceSubPanelSouth.add(dicePickValueFromDimensionComboBox2);
		diceButtonSupanel.add(diceExecuteButton);
		
		dicePanel.add(diceSubPanelNorth, BorderLayout.NORTH);
		dicePanel.add(diceSubPanelSouth, BorderLayout.CENTER);
		dicePanel.add(diceButtonSupanel, BorderLayout.SOUTH);
		
		
		//
		//	SLICE TAB
		//
		slicePanel = new JPanel(new GridBagLayout());
		sliceDimensionLabel = new JLabel("Dimension: ");
		sliceDimensionComboBox = new CustomJComboBox(sm, BIToolAction.SLICE);
		sm.addView(sliceDimensionComboBox);
		slicePickValueFromDimensionLabel = new JLabel("Value: ");
		slicePickValueFromDimensionComboBox = new CustomJComboBox(sm, BIToolAction.SLICE);
		sm.addView(slicePickValueFromDimensionComboBox);
		sliceExecuteButton = new JButton("SLICE"); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  SLICE BUTTON
		slicePanel.add(sliceDimensionLabel);
		slicePanel.add(sliceDimensionComboBox);
		slicePanel.add(slicePickValueFromDimensionLabel);
		slicePanel.add(slicePickValueFromDimensionComboBox);
		slicePanel.add(sliceExecuteButton);
		
		// Create tabbed panes and add panels.
		rollUpTabbedPane = new JTabbedPane();
		rollUpTabbedPane.setBorder(BorderFactory.createTitledBorder("ROLL UP"));
		rollUpTabbedPane.addTab("Dimension Reduction", rollUpByDimensionReductionPanel);
		rollUpTabbedPane.addTab("Climb Up Hierarchy", rollUpByClimbingHierarchyPanel);
		
		
		drillDownTabbedPane = new JTabbedPane();
		drillDownTabbedPane.setBorder(BorderFactory.createTitledBorder("DRILL DOWN"));
		drillDownTabbedPane.add("Add Dimension", drillDownAddDimensionPanel);
		drillDownTabbedPane.addTab("Descend Hierarchy", drillDownDescendHierarchyPanel);
		
		sliceAndDiceTabbedPane = new JTabbedPane();
		sliceAndDiceTabbedPane.setBorder(BorderFactory.createTitledBorder("DICE AND SLICE"));
		sliceAndDiceTabbedPane.add("Dice", dicePanel);
		sliceAndDiceTabbedPane.add("Slice", slicePanel);
		
		
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
		c.ipady = 23;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		leftPanel.add(statePanel, c);
		/******************************************************************/
		// Initialize rightPanel
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension((int) (1.2 * panelWidth), panelHeight));
		rightPanel.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
		//rightPanel.setBackground(Color.CYAN);
		
		// Create the display (JTextArea)
		display = new JTextArea();
		display.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		display.setEditable(false);
		display.setText("This is the display area");
		
		scroll = new JScrollPane(display);
		
		// Buttons for display
		lastPage = new JButton("<");
		nextPage = new JButton(">");
		
		// Reset to Central Cube Button
		resetButton = new JButton("RESET CENTRAL CUBE"); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  RESET BUTTON
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(qe.resetCube());
				sm.initialState();
				sm.notifyViews();
			}
		});
		
		// Add buttons to button panel
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(lastPage);
		buttonPanel.add(resetButton);
		buttonPanel.add(nextPage);
		/******************************************************************/
		// Add display to rightPanel
		rightPanel.add(scroll, BorderLayout.CENTER);
		
		// Add button panel to right panel
		rightPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// Add leftPanel and rightPanel to frame.
		frame.add(leftPanel);
		frame.add(rightPanel);
		
		// And finally show the frame
		frame.setVisible(true);
		/******************************************************************/
	}
	
}
