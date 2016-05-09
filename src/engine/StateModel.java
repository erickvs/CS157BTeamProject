package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		 notifyViews();
	 }
	 
	 public String newLabel(String dimension) {
		 String result = "";
		 switch(dimension) {
		 case "Time":
			 switch (time_state) {
			 case INACTIVE:
				 result = "TIME: INACTIVE";
				 break;
			 case WEEK_NUMBER_OVERALL:
				 result = "TIME:[WEEK] MONTH  YEAR";
				 break;
			 case MONTH:
				 result = "TIME: WEEK [MONTH] YEAR";
				 break;
			 case YEAR:
				 result = "TIME: WEEK  MONTH [YEAR]";
				 break;
			 }
			 break;
		 case "Store":
			 switch (store_state) {
			 case INACTIVE:
				 result = "STORE: INACTIVE";
				 break;
			 case CITY:
				 result = "STORE:[CITY] STATE  REGION";
				 break;
			 case STORE_STATE:
				 result = "STORE: CITY [STATE] REGION";
				 break;
			 case STORE_REGION:
				 result = "STORE: CITY  STATE [REGION]";
				 break;
			 }
			 break;
		 case "Promotion":
			 switch (promotion_state) {
			 case INACTIVE:
				 result = "PROMOTION: INACTIVE";
				 break;
			 case PROMOTION_NAME:
				 result = "PROMOTION: [PROMOTION_NAME]";
				 break;
			 }
			 break;
		 case "Product":
			 switch (product_state) {
			 case INACTIVE:
				 result = "PRODUCT: INACTIVE";
				 break;
			 case SUBCATEGORY:
				 result = "PRODUCT:[SUBCATEGORY] CATEGORY  DEPARTMENT";
				 break;
			 case CATEGORY:
				 result = "PRODUCT: SUBCATEGORY [CATEGORY] DEPARTMENT";
				 break;
			 case DEPARTMENT:
				 result = "PRODUCT: SUBCATEGORY  CATEGORY [DEPARTMENT]";
				 break;
			 }
			 break;
		 }
		 return result;
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
				if (time_state.getMask() < 3 && time_state.getMask() > 0) items.add("Time");
				if (store_state.getMask() < 3 && store_state.getMask() > 0) items.add("Store");
				// Cannot climb promotion hierarchy
				if (product_state.getMask() < 3 && product_state.getMask() > 0) items.add("Product");
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
				if (time_state.isActive()) items.add("Time");
				if (store_state.isActive()) items.add("Store");
				if (promotion_state.isActive()) items.add("Promotion");
				if (product_state.isActive()) items.add("Product");
				break;
		}
		 
		 
		 return items.toArray(new String[items.size()]);
	 }
	 
	 /**
	  * Notify views of a change in the model
	  */
	 public void notifyViews() {
		 
		 /** DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE */
		 String test = "\nTime: " + time_state + 
				 "\nStore: " + store_state + 
				 "\nPromotion: " + promotion_state +
				 "\nProduct: " + product_state;
		 System.out.println(test);
		  /**DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE ** DELETE **/
		 
		 for (View view: views) {
			 view.updateView();
		 }
	 }
	 
	 public void climb(String dimension) {
		 switch (dimension) {
		 case "Time":
			 time_state = (time_state == Time.WEEK_NUMBER_OVERALL) ? Time.MONTH : Time.YEAR ;
			break;
		 case "Product":
			 product_state = (product_state == Product.SUBCATEGORY) ? Product.CATEGORY : Product.DEPARTMENT;
		 	break;
		 case "Store":
			 store_state = (store_state == Store.CITY) ? Store.STORE_STATE : Store.STORE_REGION;
			break;	
		 }
		// Notify Views of a change in the state.
		 notifyViews();
	 }
	 
	 public void descend(String dimension) {
		 switch (dimension) {
		 case "Time":
			 time_state = (time_state == Time.YEAR) ? Time.MONTH : Time.WEEK_NUMBER_OVERALL ;
			break;
		 case "Product":
			 product_state = (product_state == Product.DEPARTMENT) ? Product.CATEGORY : Product.SUBCATEGORY;
		 	break;
		 case "Store":
			 store_state = (store_state == Store.STORE_REGION) ? Store.STORE_STATE : Store.CITY ;
			break;	
		 }
		// Notify Views of a change in the state.
		 notifyViews();
	 }
	 
	 public void reduce(String dimension) {
		 switch (dimension) {
		 case "Time":
			 time_state = Time.INACTIVE;
			 break;
		 case "Product":
			 product_state = Product.INACTIVE;
			 break;
		 case "Store":
			 store_state = Store.INACTIVE;
			 break;
		 case "Promotion":
			 promotion_state = Promotion.INACTIVE;
			 break;
		 }
		// Notify Views of a change in the state.
		 notifyViews();
	 }
	 
	 public void add(String dimension) {
		 switch (dimension) {
		 case "Time":
			 time_state = Time.WEEK_NUMBER_OVERALL;
			 break;
		 case "Product":
			 product_state = Product.SUBCATEGORY;
			 break;
		 case "Store":
			 store_state = Store.CITY;
			 break;
		 case "Promotion":
			 promotion_state = Promotion.PROMOTION_NAME;
			 break;
		 }
		// Notify Views of a change in the state.
		 notifyViews();
	 }
	 
	 /**
	  * Checks if all the dimensions are currently active
	  * @return
	  */
	 public boolean areAllDimensionsActive() {
		 return time_state.isActive() &&
				 product_state.isActive() &&
				 store_state.isActive() &&
				 promotion_state.isActive();
	 }
	 
	 /**
	  * Checks if all the dimensions are currently inactive
	  * @return
	  */
	 public boolean areAllDimensionsInactive() {
		 return !time_state.isActive() &&
				 !product_state.isActive() &&
				 !store_state.isActive() &&
				 !promotion_state.isActive();
	 }
	 
	 public boolean isAnyDimensionActive() {
		 return time_state.isActive() ||
				 product_state.isActive() ||
				 store_state.isActive() ||
				 promotion_state.isActive();
	 }
	 
	 public boolean isAnyDimensionInactive() {
		 return !time_state.isActive() ||
				 !product_state.isActive() ||
				 !store_state.isActive() ||
				 !promotion_state.isActive();
	 }
	 
	 public boolean canClimbMoreInAnyDimension() {
		 return (time_state.getMask() < 3 && time_state.getMask() > 0) ||
				(product_state.getMask() < 3 && product_state.getMask() > 0) ||
				(promotion_state.getMask() < 3 && promotion_state.getMask() > 0); 
			
	 }
	 
	 public boolean canDescendMoreInAnyDimension() {
		 return (time_state.getMask() > 1) ||
				(product_state.getMask() > 1) ||
				(promotion_state.getMask() > 1); 
			
	 }
	 
	 public boolean isButtonEnabled(BIToolAction biToolAction) {
		 
		 boolean result = false;
		 
		 switch (biToolAction) {
		 case ROLLUP_DIM_REDUCTION:
			 result = isAnyDimensionActive();
			 break;
		 case ROLLUP_CLIMB_HIERARCHY:
			 result = canClimbMoreInAnyDimension();
			 break;
		 case DRILLDOWN_ADD_DIM:
			 result = isAnyDimensionInactive();
			 break;
		 case DRILLDOWN_DESC_HIERARCHY:
			 result = canDescendMoreInAnyDimension();
			 break;
		 case DICE:
			 break;
		 case SLICE:
			 break;
		 }
		 
		 
		 return result;
	 }

	public Map<String, String> getActiveDimensionsAndStates() {
		Map<String, String> dimensionsAndStates = new TreeMap<String, String>();
		if(time_state.isActive()){
			dimensionsAndStates.put("Time", time_state.getState());
		}
		if(product_state.isActive()){
			dimensionsAndStates.put("Product", product_state.getState());
		}
		if(store_state.isActive()){
			dimensionsAndStates.put("Store", store_state.getState());
		}
		if(promotion_state.isActive()){
			dimensionsAndStates.put("Promotion", promotion_state.getState());
		}
		return dimensionsAndStates;
	}
}
