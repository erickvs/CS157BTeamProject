package engine;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CustomJComboBox extends JComboBox<String> implements View {

	private StateModel sm;
	private BIToolAction biToolAction;
	
	public CustomJComboBox(StateModel sm, BIToolAction biToolAction) {
		this.sm = sm;
		this.biToolAction= biToolAction;
	}
	
	
	@Override
	public void updateView() {
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>( sm.getItemsFor(biToolAction) );
		this.setModel(m);
		//this.revalidate();
		//this.repaint();
	}

	

}
