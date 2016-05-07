package engine;

import java.util.ArrayList;
import java.util.List;

public class StateModel {
	 private Time time_state;
	 private Store store_state;
	 private Promotion promotion_state;
	 private Product product_state;
	 private List<View> views;
	 
	 public StateModel() {
		 this.time_state = Time.MONTH;
		 this.store_state = Store.STORE_STATE;
		 this.promotion_state = Promotion.INACTIVE;
		 this.product_state = Product.CATEGORY;
		 this.views = new ArrayList<>();
	 }
	 
	 // Adds a new view to this model
	 public void addView(View view) {
		 this.views.add(view);
	 }
	 
	 public void initialState() {
		 this.time_state = Time.MONTH;
		 this.store_state = Store.STORE_STATE;
		 this.promotion_state = Promotion.INACTIVE;
		 this.product_state = Product.CATEGORY;
	 }
	 
	 
	 
	 public String[] getItemsFor(BIToolAction biToolAction) {
		 
		 List<String> items = new ArrayList<String>();
		 switch (biToolAction) {
			
			case ROLLUP_DIM_REDUCTION:
				// Give a list of items that are active
				if (time_state.isActive()) items.add("Time");
				if (store_state.isActive()) items.add("Store");
				if (promotion_state.isActive()) items.add("Promotion");
				if (product_state.isActive()) items.add("Product");
				break;
			
			case ROLLUP_CLIMB_HIERARCHY:
				// Give a list of items that are active
				if (time_state.getMask() < 3) items.add("Time");
				if (store_state.getMask() < 3) items.add("Store");
				// Cannot climb promotion hierarchy
				if (product_state.getMask() < 3) items.add("Product");
				break;
				
			case DRILLDOWN_ADD_DIM:
				// Give a list of items that are inactive 
				if (!time_state.isActive()) items.add("Time");
				if (!store_state.isActive()) items.add("Store");
				if (!promotion_state.isActive()) items.add("Promotion");
				if (!product_state.isActive()) items.add("Product");
				break;
				
			case DRILLDOWN_DESC_HIERARCHY:
				// Give a list of items that are active
				if (time_state.getMask() > 1) items.add("Time");
				if (store_state.getMask() > 1) items.add("Store");
				// Cannot descend promotion hierarchy
				if (product_state.getMask() > 1) items.add("Product");
				break;
				
			case DICE:
				break;
				
			case SLICE:
				break;
		}
		 
		 
		 return items.toArray(new String[items.size()]);
	 }
	 
	 /**
	  * Notify views of a change in the model
	  */
	 public void notifyViews() {
		 for (View view: views) {
			 view.updateView(view.getBiToolAction());
		 }
	 }
}
