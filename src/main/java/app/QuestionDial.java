package app;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.QuestionnareTab;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class QuestionDial extends JDialog {
	
	private final JPanel contentPanel = new JPanel();

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
			dialog.setTitle("UiA eHelse v1.21");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionDial() {
		setBounds(0, 0, 1366, 768);
		setTitle("UiA eHelse v1.21");
		setResizable(false);
		
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.SOUTH);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton cancelButton = new JButton(currentLanguage.getString("cancel"));
			cancelButton.setFont(new Font("Tahoma", Font.BOLD, 30));
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
