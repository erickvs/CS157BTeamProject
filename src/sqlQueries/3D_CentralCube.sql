/* Central Cube */
use Groceries;
select 
	store.name, store.state, product.brand,time.month, sum(dollar_sales)
from 
	store, product, `Time`, sales_fact
where 
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key 
Group by
	store.name, store.sales_state, product.brand, time.month
	