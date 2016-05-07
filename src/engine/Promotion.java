package engine;

public enum Promotion {
	INACTIVE(0, false), PROMOTION_NAME(1, true);
	private final boolean active;
	private final int mask;
	private Promotion(int mask, boolean valid) {
		this.mask = mask;
		this.active = valid;
	}
    public int getMask(){return mask;}
    public boolean isActive(){return active;}
}
