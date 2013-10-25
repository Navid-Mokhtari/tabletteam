import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionnareTab extends JComponent {
	private static final long serialVersionUID = -7594299439504858239L;
	

	public Component getView() {
		JComponent questionnare = createPanel("My questions");
		questionnare.setPreferredSize(new Dimension(600, 600));
		return questionnare;
	}
//
//	protected JComponent makeTextPanel(String text) {
//		JPanel panel = new JPanel(false);
//		JLabel filler = new JLabel(text);
//		filler.setHorizontalAlignment(JLabel.CENTER);
//		panel.setLayout(new GridLayout(1, 1));
//		panel.add(filler);
//		return panel;
//	}
	public JPanel createPanel(String title) {
        JPanel panel = new JPanel();

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
        
        
//        panel.setLayout(new BorderLayout());
//        panel.add(new JLabel(title), BorderLayout.NORTH);   
//        JLabel questionOne = new JLabel("Q1: How do you feel today?");
//        questionOne.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
//        questionOne.add(new JLabel(title), BorderLayout.AFTER_LAST_LINE);
//        panel.add(questionOne);
        return panel;
    }
//	public JPanel QuestionnarePanel(){
//		JPanel questionOne = new JPanel();
//		questionOne.set
//		questionOne.add(questionOne, 1);
//		JLabel lblquesitonOne = new JLabel();
//		lblquesitonOne.add(lblquesitonOne, 2);
//		
//		return questionOne;
//		
//	}
}
