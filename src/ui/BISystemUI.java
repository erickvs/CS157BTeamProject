package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BISystemUI {
	
	/*********************   FRAME   **********************/
	private static JFrame frame;
	private static int frameWidth = 1300;
	private static int frameHeight = 700;
	/******************************************************/
	
	/******************* STATE PANEL **********************/
	private static JPanel statePanel;
	/******************************************************/
	
	
	/*************** LEFT AND RIGHT PANELS ****************/
	private static JPanel leftPanel;
	private static JPanel rightPanel;
	
	private static int panelWidth  =  
			(int)(490.0 / 1000 * frameWidth) ;
	
	private static int panelHeight =  
			(int)(465.0 / 500  * frameHeight) ;
	/******************************************************/
	
	/************** RIGHT PANEL COMPONENTS ****************/
	private static JTextArea display;
	private static JButton nextPage;
	private static JButton lastPage;
	private static JPanel buttonPanel;
	/******************************************************/
	
	/*************** LEFT PANEL COMPONENTS ****************/
	// CONSTANTS
	private final static int LEFT_PANEL_ROWS = 4;
	private final static int LEFT_PANEL_COLUMNS = 1;
	
	// Tabbed Panes
	private static JTabbedPane rollUpTabbedPane;
	private static JTabbedPane drillDownTabbedPane;
	private static JTabbedPane sliceAndDiceTabbedPane;
	
	// Elements for "Roll Up - Dimension Reduction" tab
	private static JPanel rollUpByDimensionReductionPanel;
	private static JLabel rollUpDimReducLabel;
	private static JComboBox<String> rollUpDimReducComboBox;
	private static JButton rollUpDimReducButton;
	
	// Elements for "Roll Up - Climbing Hierarchy" tab
	private static JPanel rollUpByClimbingHierarchyPanel;
	private static JLabel rollUpClimHierarLabel;
	private static JComboBox<String>  rollUpClimHierarComboBox;
	private static JButton rollUpClimHierarButton;
	
	// Elements for "Drill Down - Add Dimension" tab
	private static JPanel drillDownAddDimensionPanel;
	private static JLabel drillDownAddDimLabel;
	private static JComboBox<String> drillDownAddDimComboBox;
	private static JButton drillDownAdddimButton;
	
	// Elements for "Drill Down - Descend Hierarchy" tab
	private static JPanel drillDownDescendHierarchyPanel;
	private static JLabel drillDownDescHierarLabel;
	private static JComboBox<String> drillDownDescHierarComboBox;
	private static JButton drillDownDescHierarButton;
	
	// Elements for "Dice"
	private static JPanel dicePanel;
	private static JLabel dicePickDimensionLabel;
	private static JComboBox<String> dicePickDimensionComboBox;
	private static JLabel dicePickValueFromDimensionLabel;
	private static JComboBox<String> dicePickValueFromDimensionComboBox;
	private static JButton diceExecuteButton;
	
	// Elements for "Slice"
	private static JPanel slicePanel;
	private static JLabel sliceDimensionLabel;
	private static JComboBox<String> sliceDimensionComboBox;
	private static JButton sliceExecuteButton;
	/******************************************************/
	
	/****************STATE PANEL COMPONENTS****************/
	private static JLabel timeLabel;
	private static JLabel storeLabel;
	private static JLabel promotionLabel;
	private static JLabel productLabel;
	/******************************************************/
	
	public static void run() {
		/*** DELETE BEGIN *****/
		System.out.println("Panel Width: " +  panelWidth);
		System.out.println("panel Height: " + panelHeight);
		/*** DELETE END *****/
		
		
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
		
		timeLabel = new JLabel("TIME: DAY [MONTH] QUARTER YEAR");
		timeLabel.setForeground(Color.BLUE);
		timeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		storeLabel = new JLabel("STORE: CITY COUNTY [STATE] DISTRICT COUNTRY");
		storeLabel.setForeground(Color.BLUE);
		storeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		promotionLabel = new JLabel("PROMOTION: BLAH [BLAH] BLAH");
		promotionLabel.setForeground(Color.BLUE);
		promotionLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		productLabel = new JLabel("PRODUCT: CHEETOS [PAPER] TOWELS BLAH BLAH");
		productLabel.setForeground(Color.BLUE);
		productLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		statePanel.add(timeLabel);
		statePanel.add(storeLabel);
		statePanel.add(promotionLabel);
		statePanel.add(productLabel);
		/******************************************************************/
		
		// Initialize leftPanel
		leftPanel = new JPanel(new GridLayout(LEFT_PANEL_ROWS, LEFT_PANEL_COLUMNS));
		leftPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		
		
		//
		//	ROLL UP BY DIMENTION REDUCTION TAB
		//
		rollUpByDimensionReductionPanel = new JPanel(new GridBagLayout());
		rollUpDimReducLabel = new JLabel("Dimension: ");
		rollUpDimReducComboBox = new JComboBox<>(); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR ROLLUP DIMREDUC
		rollUpDimReducButton = new JButton("ROLL UP");
		rollUpByDimensionReductionPanel.add(rollUpDimReducLabel);
		rollUpByDimensionReductionPanel.add(rollUpDimReducComboBox);
		rollUpByDimensionReductionPanel.add(rollUpDimReducButton);
		
		//
		//	ROLL UP BY CLIMBING HIERARCHY TAB
		//
		rollUpByClimbingHierarchyPanel = new JPanel(new GridBagLayout());
		rollUpClimHierarLabel = new JLabel("Dimension: ");
		rollUpClimHierarComboBox = new JComboBox<>(); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR ROLLUP CLIMB HIERARCHY
		rollUpClimHierarButton = new JButton("ROLL UP");
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarLabel);
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarComboBox);
		rollUpByClimbingHierarchyPanel.add(rollUpClimHierarButton);
		
		//
		//	DRILL DOWN BY ADDING DIMENSION TAB
		//
		drillDownAddDimensionPanel =  new JPanel(new GridBagLayout());
		drillDownAddDimLabel = new JLabel("Dimension: ");
		drillDownAddDimComboBox = new JComboBox<>(); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR DRILLDOWN ADDDIM
		drillDownAdddimButton = new JButton("DRILL DOWN");
		drillDownAddDimensionPanel.add(drillDownAddDimLabel);
		drillDownAddDimensionPanel.add(drillDownAddDimComboBox);
		drillDownAddDimensionPanel.add(drillDownAdddimButton);
		
		//
		//	DRILL DOWN BY CLIMBING HIERARCHY TAB
		//
		drillDownDescendHierarchyPanel = new JPanel(new GridBagLayout());
		drillDownDescHierarLabel = new JLabel("Dimension: ");
		drillDownDescHierarComboBox = new JComboBox<>(); //<<<<<<<<<<<<<<<<<< ADD LIST OF ITEMS HERE.. IMPLEMENT MVC FOR DRILLDOWN CLIMB HIER
		drillDownDescHierarButton = new JButton("DRILL DOWN");
		drillDownDescendHierarchyPanel.add(drillDownDescHierarLabel);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarComboBox);
		drillDownDescendHierarchyPanel.add(drillDownDescHierarButton);
		
		//
		//	DICE TAB
		//
		dicePanel = new JPanel(new GridBagLayout());
		dicePickDimensionLabel = new JLabel("Dimension: ");
		dicePickDimensionComboBox = new JComboBox<>();
		dicePickValueFromDimensionLabel = new JLabel("Value: ");
		dicePickValueFromDimensionComboBox = new JComboBox<>();
		diceExecuteButton = new JButton("DICE");
		dicePanel.add(dicePickDimensionLabel);
		dicePanel.add(dicePickDimensionComboBox);
		dicePanel.add(dicePickValueFromDimensionLabel);
		dicePanel.add(dicePickValueFromDimensionComboBox);
		dicePanel.add(diceExecuteButton);
		
		
		//
		//	SLICE TAB
		//
		slicePanel = new JPanel(new GridBagLayout());
		sliceDimensionLabel = new JLabel("Dimension: ");
		sliceDimensionComboBox = new JComboBox<>();
		sliceExecuteButton = new JButton("SLICE");
		slicePanel.add(sliceDimensionLabel);
		slicePanel.add(sliceDimensionComboBox);
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
		leftPanel.add(rollUpTabbedPane);
		leftPanel.add(drillDownTabbedPane);
		leftPanel.add(sliceAndDiceTabbedPane);
		leftPanel.add(statePanel);
		/******************************************************************/
		// Initialize rightPanel
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		rightPanel.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
		//rightPanel.setBackground(Color.CYAN);
		
		// Create the display (JTextArea)
		display = new JTextArea();
		display.setEditable(false);
		display.setText("This is the display area");
		
		// Buttons for display
		lastPage = new JButton("<");
		nextPage = new JButton(">");
		
		// Add buttons to button panel
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(lastPage);
		buttonPanel.add(nextPage);
		/******************************************************************/
		// Add display to rightPanel
		rightPanel.add(display, BorderLayout.CENTER);
		
		// Add button panel to right panel
		rightPanel.add(buttonPanel, BorderLayout.NORTH);
		
		// Add leftPanel and rightPanel to frame.
		frame.add(leftPanel);
		frame.add(rightPanel);
		
		// And finally show the frame
		frame.setVisible(true);
		/******************************************************************/
		
	}
}
