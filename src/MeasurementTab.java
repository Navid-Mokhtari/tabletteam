import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MeasurementTab extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3958650284297145802L;
	
	private JLabel pulseJLabel;
	private static JLabel pulseValue;
	private JLabel oxigenJLabel;
	private static JLabel oxigenValue;
	private JLabel timeLabel;
	private static JLabel timeValue;
	private JLabel statusJLabel;
	
	public Component getView() {
		JComponent measurements = createPanel("My Measurements");
		measurements.setPreferredSize(new Dimension(600,600));
		return measurements;
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	public JPanel createPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setLayout(new BorderLayout());
//        panel.add(new JLabel(title), BorderLayout.NORTH);
        
        GridBagConstraints constrains = new GridBagConstraints();
		constrains.insets = new Insets(5, 5, 5, 5);
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.anchor=GridBagConstraints.FIRST_LINE_START;
		statusJLabel = new JLabel("Demoversion");
		constrains.gridx = 0;
		constrains.gridy = 0;
		panel.add(statusJLabel, constrains);

		pulseJLabel = new JLabel("Pulse:");
		constrains.gridx = 0;
		constrains.gridy = 1;
		panel.add(pulseJLabel, constrains);

		pulseValue = new JLabel("85");
		constrains.gridx = 1;
		constrains.gridy = 1;
		panel.add(pulseValue, constrains);

		oxigenJLabel = new JLabel("Oxigen:");
		constrains.gridx = 0;
		constrains.gridy = 2;
		panel.add(oxigenJLabel, constrains);

		oxigenValue = new JLabel("98");
		constrains.gridx = 1;
		constrains.gridy = 2;
		panel.add(oxigenValue, constrains);

		timeLabel = new JLabel("Time:");
		constrains.gridx = 0;
		constrains.gridy = 3;
		panel.add(timeLabel, constrains);

		timeValue = new JLabel("12:30:13");
		constrains.gridx = 1;
		constrains.gridy = 3;
		panel.add(timeValue, constrains);
		
		
		
		
        return panel;
    }
}
