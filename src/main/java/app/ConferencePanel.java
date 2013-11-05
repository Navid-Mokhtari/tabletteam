package app;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConferencePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 975058181186956453L;
	private static JLabel pulseValue;
	

	public ConferencePanel(GridBagLayout gridBagLayout) {
		super(gridBagLayout);
		
		pulseValue = new JLabel("Not available");
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.gridx = 1;
		constrains.gridy = 1;
		this.add(pulseValue, constrains);
	}
	
}
