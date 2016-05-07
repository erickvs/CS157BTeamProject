package engine;

import javax.swing.JComboBox;

public class CustomJComboBox extends JComboBox<String> implements View {

	StateModel sc;
	
	public CustomJComboBox(StateModel sc) {
		this.sc = sc;
	}
	
	@Override
	public void updateView() {
		
	} 

}
