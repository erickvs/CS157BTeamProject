
/*The Slice operation performs a selection on one dimension of the given cube, 
resulting in a sub cube.*/

use Groceries;
select 
	store.name, store.store_state, product.brand,time.month, sum(dollar_sales)
from 
	store, product, `Time`, sales_fact
where 
	product.brand = 'Chewy Industries' and
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key 
Group by
	store.name, store.store_state, product.brand, time.month
	