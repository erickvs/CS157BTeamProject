/*The Dice operation defines a sub cube by performing a selection on one or more dimensions*/

use Groceries;
select 
	store.name, store.store_state, product.brand,time.month, sum(dollar_sales)
from 
	store, product, `Time`, sales_fact
where 
	product.brand = ('Chewy Industries') and
    store.name = ('Store No. 1' ) and
    /*time.month = ('34638' or '34699') and*/
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key 
Group by
	store.name, store.store_state, product.brand, time.month
	