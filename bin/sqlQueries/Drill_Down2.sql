 /* Drill down by adding a dimension */
 /* add promotion.promotion_name*/
 
use Groceries;
select 
	store.name, store.store_state, product.brand,time.month, Promotion.promotion_name, 
    sum(dollar_sales)
from 
	store, product, `Time`, sales_fact, Promotion
where 
	store.store_key = sales_fact.store_key and
	product.product_key = sales_Fact.product_key and
	Time.time_key = sales_fact.time_key and
    promotion.promotion_key = sales_fact.promotion_key
Group by
	store.name, store.store_state, product.brand, Promotion.promotion_name, time.month
	