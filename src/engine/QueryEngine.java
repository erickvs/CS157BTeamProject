package engine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JTextArea;

import ui.*;

public class QueryEngine {
	
	/*********************   JDBC    **********************/
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Groceries";
	static final String USER = "root";
	static final String PASS = "pa$$w0rd"; // not my actual password
	/******************************************************/
	
	StateModel sm;
	
	// This is to notify the SQL Display in the gui
	private JTextArea ta;
	public void setSqlDisplay(JTextArea ta) {this.ta = ta;}
	public void notifyTextAreaOfSQL(String sql) {this.ta.setText(sql);}
	
	
	/**
	 * Initializes states model
	 */
	public QueryEngine(StateModel sm) {
		this.sm = sm;
	}
	
	public static String initialCentralCubeSQL() {
		return 
		"SELECT " + 
			"Store.store_state 'STORE STATE', " + 
			"Product.category 'PRODUCT CATEGORY', "+
			"Time.month 'MONTH',  "+
			"ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS' "+
		"FROM Store, Product, Time, sales_fact "+
		"WHERE Store.store_key = Sales_Fact.store_key and "+
			"Product.product_key = Sales_Fact.product_key and "+
			"Time.time_key = Sales_Fact.time_key "+
		"GROUP BY Store.store_state, Product.category, Time.month;";
	}
	
	
	/**
	 * Changes the state of the current cube to the initial central cube.
	 * @return
	 */
	public static String resetCube() {
		String query = initialCentralCubeSQL();
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		
		String store_state = "";
		String product_category = "";
		int month = 0;
		double sales = 0;
		
		String result = "";
		result += String.format("|%17s | %17s | %17s |%17s |%n", "Product", "Store",  "Time", "Sales");
		result += "\n";
		
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
				result += String.format("|%17s | %17s | %17d | %17s |%n", product_category, store_state,  month, sales);
			}
		} catch(Exception se) {
			se.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Creates a query depending on the biToolAction (ex: "rollup")
	 * @param biToolAction
	 * @return
	 */
	public String createQuery(BIToolAction biToolAction, String dimension) {
		String result = "\n";
		// Database stuff would go here, for now just focus on model object transitions
		switch(biToolAction) {
			case ROLLUP_CLIMB_HIERARCHY:
				sm.climb(dimension);
				break;
			case ROLLUP_DIM_REDUCTION:
				sm.reduce(dimension);
				break;
			case DRILLDOWN_DESC_HIERARCHY:
				sm.descend(dimension);
				break;
			case DRILLDOWN_ADD_DIM:
				sm.add(dimension);
				break;
		}
		result += this.executeQueryForCurrentState();
		return result;
	}
	/*
	 * Generates a OLAP 
	 */
	private String generateSql(Map<String, String> dimensionToStates, boolean hasFilters, Map<String, String> dimensionFilters) {
		//Building the SELECT clause
		String query = "SELECT ";
		for(String s : dimensionToStates.keySet()) {
			query = query + s + "." + dimensionToStates.get(s) + ", ";
		}
		query += "ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS' ";
		
		//Building the FROM clause
		query += "FROM ";
		for(String s : dimensionToStates.keySet()){
			query += s + ", ";
		}
		query += "Sales_Fact ";
		
		
		//Building the WHERE clause
		Object[] dimensions = dimensionToStates.keySet().toArray();
		Object[] currentStates = dimensionToStates.values().toArray();
		query += "WHERE ";
		if(hasFilters){
			Object[] filterDimensions = dimensionFilters.keySet().toArray();
			for(int i = 0; i < filterDimensions.length; i++){
				String dimenToFilter = (String) filterDimensions[i];
				String escapedFilterValue = "";
				if(dimenToFilter.equals("Time")){
					escapedFilterValue = dimensionFilters.get(dimenToFilter); 
				} else {
					escapedFilterValue = "'" + dimensionFilters.get(dimenToFilter) + "'";
				}
				query += dimenToFilter +"."+dimensionToStates.get(dimenToFilter) + " = " + escapedFilterValue + " AND ";
			}
		}
		
		for(int i = 0; i < dimensions.length; i++){
			String s = (String) dimensions[i];
			query += s + "." + s.toLowerCase() + "_key = Sales_Fact." + s.toLowerCase() + "_key ";
			if(i < dimensions.length -1){
				query += "AND ";
			}
		}
		
		//Building the GROUP BY clause
		query += "GROUP BY ";
		for(int i = 0; i < dimensions.length; i++){
			String dimension = (String) dimensions[i];
			String state = (String) currentStates[i];
			query += dimension + "." + state;
			if(i < dimensions.length - 1){
				query += ", ";
			}
		}
		notifyTextAreaOfSQL("\n"+query); // For the SQL Display.
		return query;
	}
	
	public String executeQueryForCurrentState() {
		Map<String, String> dimensionToStates = sm.getActiveDimensionsAndStates();
		Object[] dimensions = dimensionToStates.keySet().toArray();
		String query = this.generateSql(dimensionToStates, false, null);
		
		//System.out.println("Dynamically created query: " + query);
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		
		String store_state = "";
		String product_category = "";
		int month = 0;
		double sales = 0;
		String result = "";
		for(int i = 0; i < dimensions.length; i++){
			String dimension = (String) dimensions[i];
			result += String.format("|%17s ", dimension);
		}
		result += String.format("|%17s ", "Sales");
		result += "|\n\n";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				List<String> states = new ArrayList<>();
				
				for(int i = 1; i <= dimensions.length + 1; i++){
					states.add(rs.getString(i));
				}
				for(String s : states){
					result += String.format("|%17s ", s);
				}
				result += "|\n";
				
//				store_state = rs.getString(1);
//				product_category = rs.getString(2);
//				month = rs.getInt(3);
//				sales = rs.getDouble(4);
//				result += String.format("|%6s | %8s | %5d | $%9.2f |%n", store_state, product_category, month, sales);
			}
			//result += " --------------------------------------- \n";
		} catch(Exception se) {
			se.printStackTrace();
		}
		return result;
	}

	public String dice(Map<String, String> dimensionFilters){
		Map<String, String> dimensionsToStates = sm.getActiveDimensionsAndStates();
		Object[] dimensions = dimensionsToStates.keySet().toArray();
		String query = this.generateSql(dimensionsToStates, true, dimensionFilters);
		//System.out.println("Dynamically created query: " + query);
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		
		String store_state = "";
		String product_category = "";
		int month = 0;
		double sales = 0;
		String result = "";
		for(int i = 0; i < dimensions.length; i++){
			String dimension1 = (String) dimensions[i];
			result += String.format("|%17s ", dimension1);
		}
		result += String.format("|%17s ", "Sales");
		result += "|\n\n";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				List<String> states = new ArrayList<>();
				
				for(int i = 1; i <= dimensions.length + 1; i++){
					states.add(rs.getString(i));
				}
				for(String s : states){
					result += String.format("|%17s ", s);
				}
				result += "|\n";
				
//				store_state = rs.getString(1);
//				product_category = rs.getString(2);
//				month = rs.getInt(3);
//				sales = rs.getDouble(4);
//				result += String.format("|%6s | %8s | %5d | $%9.2f |%n", store_state, product_category, month, sales);
			}
			//result += " --------------------------------------- \n";
		} catch(Exception se) {
			se.printStackTrace();
		}
		return result;
	}

	public String slice(String dimension, String dimensionValue) {
		Map<String, String> dimensionsToStates = sm.getActiveDimensionsAndStates();
		Object[] dimensions = dimensionsToStates.keySet().toArray();
		Map<String, String> dimensionFilters = new TreeMap<>();
		dimensionFilters.put(dimension, dimensionValue);
		String query = this.generateSql(dimensionsToStates, true, dimensionFilters);
		//System.out.println("Dynamically created query: " + query);
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		
		String store_state = "";
		String product_category = "";
		int month = 0;
		double sales = 0;
		String result = "";
		for(int i = 0; i < dimensions.length; i++){
			String dimension1 = (String) dimensions[i];
			result += String.format("|%17s ", dimension1);
		}
		result += String.format("|%17s ", "Sales");
		result += "|\n\n";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				List<String> states = new ArrayList<>();
				
				for(int i = 1; i <= dimensions.length + 1; i++){
					states.add(rs.getString(i));
				}
				for(String s : states){
					result += String.format("|%17s ", s);
				}
				result += "|\n";
				
//				store_state = rs.getString(1);
//				product_category = rs.getString(2);
//				month = rs.getInt(3);
//				sales = rs.getDouble(4);
//				result += String.format("|%6s | %8s | %5d | $%9.2f |%n", store_state, product_category, month, sales);
			}
			//result += " --------------------------------------- \n";
		} catch(Exception se) {
			se.printStackTrace();
		}
		return result;
	}
	
	
}
