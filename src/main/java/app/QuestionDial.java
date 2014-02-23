package app;
import java.awt.BorderLayout;

import app.QuestionnareTab;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JPanel;


public class QuestionDial extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1718779024013326249L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionDial dialog = new QuestionDial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionDial() {
		setBounds(0, 0, 1366, 768);
		
		getContentPane().setLayout(new BorderLayout());
			
			
		JPanel panel12 = new JPanel();
		getContentPane().add(panel12, BorderLayout.CENTER);
		QuestionnareTab questionnareTab=new QuestionnareTab();
			try {
				panel12 = questionnareTab.createPanel("HHH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		panel12.setVisible(true);
		getContentPane().add(panel12);
					
	}

}
