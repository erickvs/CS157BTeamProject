package engine;

public enum Product {
	INACTIVE(0, false), SUBCATEGORY(1, true), CATEGORY(2, true), DEPARTMENT(3, true);
	private final boolean active;
	private final int mask;
	private Product(int mask, boolean valid) {
		this.mask = mask;
		this.active = valid;
	}
    public int getMask(){return mask;}
    public boolean isActive(){return active;}
    public String getState(){
    	if(mask == 1){
    		return "Subcategory";
    	} else if(mask == 2){
    		return "Category";
    	} else if(mask == 3){
    		return "Department";
    	}
    	return "Category";
    }
}
