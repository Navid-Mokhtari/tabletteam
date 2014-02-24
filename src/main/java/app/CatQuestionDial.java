package app;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CatQuestionDial extends JDialog {
	
	private final JPanel contentPanel = new JPanel();

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
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.SOUTH);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					}
			});
			
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
