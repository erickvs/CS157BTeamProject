 /* 
	Drill down by climbing down a concept hierarchy by one level
	Rather than group data by month, group by year.
 */
 
USE GROCERIES;
SELECT 
	Store.store_state 'STORE STATE', 
	Product.category 'PRODUCT CATEGORY', 
	Time.year 'YEAR',  
	ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS'
FROM 
	Store, Product, Time, sales_fact
WHERE 
	Store.store_key = Sales_Fact.store_key and
	Product.product_key = Sales_Fact.product_key and
	Time.time_key = Sales_Fact.time_key 
GROUP BY
	Store.store_state, Product.category, Time.year