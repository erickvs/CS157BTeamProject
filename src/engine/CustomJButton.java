package engine;

import javax.swing.JButton;

public class CustomJButton extends JButton implements View{

	StateModel sm;
	BIToolAction biToolAction;
	
	public CustomJButton(String buttonName, StateModel sm, BIToolAction biToolAction) {
		this.sm = sm;
		this.biToolAction = biToolAction;
		setText(buttonName);
	}
	
	
	@Override
	public void updateView() {
		this.setEnabled(sm.isButtonEnabled(this.biToolAction));
	}

}
