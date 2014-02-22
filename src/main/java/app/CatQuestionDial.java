package app;
import java.awt.BorderLayout;

import app.QuestionnareTab;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CatQuestionDial extends JDialog {

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
		{
			
			//
			{
				//JPanel panel3=null;
				
				//JPanel = questionnareTab.createPanel("HHH");
			
	//		getContentPane().add(questionnareTab,BorderLayout.CENTER);
			}
			
			//
			
			 
			
			
		}
		
		{
			
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
		
		//
		
		
		
	}

}
