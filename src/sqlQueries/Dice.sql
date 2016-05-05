/*The Dice operation defines a sub cube by performing a selection on one or more dimensions*/

USE GROCERIES;
SELECT 
	Store.store_state 'STORE STATE', 
	Product.category 'PRODUCT CATEGORY', 
	Time.month 'MONTH',  
	ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS'
FROM 
	Store, Product, Time, sales_fact
WHERE 
	Store.store_state = 'CA' and
	Product.category = 'food' and
	Store.store_key = Sales_Fact.store_key and
	Product.product_key = Sales_Fact.product_key and
	Time.time_key = Sales_Fact.time_key 
GROUP BY
	Store.store_state, Product.category, Time.month
	