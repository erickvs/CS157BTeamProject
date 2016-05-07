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
	public void updateView(BIToolAction biToolAction) {
		DefaultComboBoxModel m;
		switch (biToolAction) {
		
			case ROLLUP_DIM_REDUCTION:
				// Get info from StateModel object so you can fill
				// the JComboBox with the appropriate array
				// The same for all the other cases here.
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.ROLLUP_DIM_REDUCTION) );
				setModel(m);
				break;
			
			case ROLLUP_CLIMB_HIERARCHY:
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.ROLLUP_CLIMB_HIERARCHY) );
				setModel(m);
				break;
				
			case DRILLDOWN_ADD_DIM:
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.DRILLDOWN_ADD_DIM) );
				setModel(m);
				break;
				
			case DRILLDOWN_DESC_HIERARCHY:
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.DRILLDOWN_DESC_HIERARCHY) );
				setModel(m);
				break;
				
			case DICE:
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.DICE) );
				setModel(m);
				break;
				
			case SLICE:
				m = new DefaultComboBoxModel( sm.getItemsFor(biToolAction.SLICE) );
				setModel(m);
				break;
		}
	}

	@Override
	public BIToolAction getBiToolAction() {
		return biToolAction;
	}

}
