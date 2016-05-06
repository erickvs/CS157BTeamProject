package engine;

import java.sql.*;

public class QueryEngine {
	
	/*********************   JDBC    **********************/
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Groceries";
	static final String USER = "root";
	static final String PASS = "pa$$w0rd"; // not my actual password
	/******************************************************/
	
	
	public static String resetCube() {
		
		String query = 
		"SELECT Store.store_state 'STORE STATE', Product.category 'PRODUCT CATEGORY', Time.month 'MONTH',  ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS' "+
		"FROM Store, Product, Time, sales_fact "+
		"WHERE Store.store_key = Sales_Fact.store_key and Product.product_key = Sales_Fact.product_key and Time.time_key = Sales_Fact.time_key GROUP BY Store.store_state, Product.category, Time.month;";

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
}
