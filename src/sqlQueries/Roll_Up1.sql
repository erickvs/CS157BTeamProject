 /* Roll Up by climbing up a concept hierarchy by one level
	Rather than group data by state, group by region
 */
 
 use groceries;
select 
	store.name, store.sales_region, product.brand,time.month, sum(dollar_sales)
from 
	store, product, `Time`, sales_fact
where 
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key 
Group by
	store.name, store.sales_region, product.brand, time.month