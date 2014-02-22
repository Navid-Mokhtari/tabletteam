package app;
import java.awt.BorderLayout;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JPanel;


public class CatQuestionDial extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1161175344711505174L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CatQuestionDial dialog = new CatQuestionDial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CatQuestionDial() {
		setBounds(0, 0, 1366, 768);
		
		getContentPane().setLayout(new BorderLayout());
			
		JPanel panel12 = new JPanel();
		getContentPane().add(panel12, BorderLayout.CENTER);
		CatQuestionnaire catquestionnaire=new CatQuestionnaire();
			try {
				panel12 = catquestionnaire.createPanel("HHH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		panel12.setVisible(true);
		getContentPane().add(panel12);
	}

}
