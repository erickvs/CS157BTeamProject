/* Drill down by climbing down a concept hierarchy by one level
	Rather than group data by state, group by district
 */
 
use Groceries;
select 
	store.name, store.sales_district, product.brand,time.month, sum(dollar_sales)
from 
	store, product, `Time`, sales_fact
where 
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key 
Group by
	store.name, store.sales_district, product.brand, time.month
	