package ui;

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
	private static JLabel dicePickDimensionLabel1;
	private static JComboBox<String> dicePickDimensionComboBox1;
	private static JLabel dicePickValueFromDimensionLabel1;
	private static JComboBox<String> dicePickValueFromDimensionComboBox1;
	private static JLabel dicePickDimensionLabel2;
	private static JComboBox<String> dicePickDimensionComboBox2;
	private static JLabel dicePickValueFromDimensionLabel2;
	private static JComboBox<String> dicePickValueFromDimensionComboBox2;
	
	private static JButton diceExecuteButton;
	
	// Elements for "Slice"
	private static JPanel slicePanel;
	private static JLabel sliceDimensionLabel;
	private static JComboBox<String> sliceDimensionComboBox;
	private static JLabel slicePickValueFromDimensionLabel;
	private static JComboBox<String> slicePickValueFromDimensionComboBox;
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
		dicePanel = new JPanel(new BorderLayout());
		
		JPanel diceSubPanelNorth = new JPanel();
		JPanel diceSubPanelSouth = new JPanel();
		JPanel diceButtonSupanel = new JPanel();
		diceButtonSupanel.setLayout(new FlowLayout());
		
		dicePickDimensionLabel1 = new JLabel("Dim1:");
		dicePickDimensionComboBox1 = new JComboBox<>();
		dicePickValueFromDimensionLabel1 = new JLabel("Val:");
		dicePickValueFromDimensionComboBox1 = new JComboBox<>();
		
		dicePickDimensionLabel2 = new JLabel("Dim2:");
		dicePickDimensionComboBox2 = new JComboBox<>();
		dicePickValueFromDimensionLabel2 = new JLabel("Val:");
		dicePickValueFromDimensionComboBox2 = new JComboBox<>();
		
		
		diceExecuteButton = new JButton("DICE");
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
		sliceDimensionComboBox = new JComboBox<>();
		slicePickValueFromDimensionLabel = new JLabel("Value: ");
		slicePickValueFromDimensionComboBox = new JComboBox<>();
		sliceExecuteButton = new JButton("SLICE");
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
		resetButton = new JButton("RESET CENTRAL CUBE");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = createQuery("reset");
				display.setText(executeSQL(query));
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
	private static String executeSQL(String query) {
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		
		String store_state = "";
		String product_category = "";
		int month = 0;
		double sales = 0;
		
		String result = "";
		result += " --------------------------------------- \n";
		result += String.format("|%6s | %8s | %5s |%11s |%n", "State", "Category", "Month", "Sales");
		result += " --------------------------------------- \n";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				store_state = rs.getString(1);
				product_category = rs.getString(2);
				month = rs.getInt(3);
				sales = rs.getDouble(4);
				result += String.format("|%6s | %8s | %5d | $%9.2f |%n", store_state, product_category, month, sales);
			}
			result += " --------------------------------------- \n";
			
			
		} catch(Exception se) {
			se.printStackTrace();
		}
		
		
		
		return result;
	}
	
	private static String createQuery(String action) {		
		String query = null;
			if (action.equals("reset")) {
				query = "SELECT Store.store_state 'STORE STATE', Product.category 'PRODUCT CATEGORY', Time.month 'MONTH',  ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS' FROM Store, Product, Time, sales_fact WHERE Store.store_key = Sales_Fact.store_key and Product.product_key = Sales_Fact.product_key and Time.time_key = Sales_Fact.time_key GROUP BY Store.store_state, Product.category, Time.month;";
			}
			
			else if (action.equals("rollup")) {
				query = "";
			}
		return query;
	}
}
