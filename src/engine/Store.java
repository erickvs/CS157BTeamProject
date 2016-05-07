package engine;

public enum Store {
	INACTIVE(0, false), CITY(1, true), STORE_STATE(2, true), STORE_REGION(3, true);
	private final boolean active;
	private final int mask;
	private Store(int mask, boolean active) {
		this.mask = mask;
		this.active = active;
	}
    public int getMask(){return mask;}
    public boolean isActive(){return active;}
}
