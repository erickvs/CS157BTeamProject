 /* Drill down by adding a dimension */
 /* add promotion.promotion_name*/
 
USE GROCERIES;
SELECT 
	Store.store_state 'STORE STATE', 
	Product.category 'PRODUCT CATEGORY', 
	Time.month 'MONTH', 
	Promotion.promotion_name 'PROMOTION',
	ROUND(SUM(Sales_Fact.dollar_sales), 2) AS 'SALES IN DOLLARS'
FROM 
	store, product, Time, sales_fact, Promotion
WHERE 
	Store.store_key = Sales_Fact.store_key and
	Product.product_key = Sales_Fact.product_key and
	Time.time_key = Sales_Fact.time_key and
    Promotion.promotion_key = Sales_Fact.promotion_key
GROUP BY
	Store.store_state, Product.category, Time.month, 
	Promotion.promotion_name
	