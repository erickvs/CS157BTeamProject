package engine;

public enum Time {
	INACTIVE(0,false), WEEK_NUMBER_OVERALL(1, true), MONTH(2, true), YEAR(3, true);
	private final boolean active;
	private final int mask;
	private Time(int mask, boolean valid) {
		this.mask = mask;
		this.active = valid;
	}
    public int getMask(){return mask;}
    public boolean isActive(){return active;}
    public String getState(){
    	if(mask == 1){
    		return "Week_number_overall";
    	} else if (mask == 2){
    		return "Month";
    	} else if (mask == 3){
    		return "Year";
    	} 
    	return "Month";
    }
    
}
