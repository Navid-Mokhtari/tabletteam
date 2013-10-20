import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class QuestionnareTab extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7594299439504858239L;
	public Component getView()
	{
		JComponent questionnare = makeTextPanel("My questions");
		return questionnare;
	}
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

}
