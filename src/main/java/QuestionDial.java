import java.awt.BorderLayout;

import app.QuestionnareTab;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class QuestionDial extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 858, 636);
	
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
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
			getContentPane().add(panel12, BorderLayout.NORTH);
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
		
		//
		
		
		
	}

}
