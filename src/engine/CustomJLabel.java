package engine;

import javax.swing.JLabel;

public class CustomJLabel extends JLabel implements View{

	private StateModel sm;
	private String dimension;
	
	public CustomJLabel(StateModel sm, String dimension) {
		this.sm = sm;
		this.dimension= dimension;
	}
	
	@Override
	public void updateView() {
		setText(sm.newLabel(dimension));
	}

	@Override
	public BIToolAction getBiToolAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
