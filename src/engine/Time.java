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
    
}
