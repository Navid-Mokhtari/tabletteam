import app.Tabs;
import app.MeasurementTab;
import app.HealthProperties;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.Box;

import java.awt.Component;

import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JSeparator;

import app.App;
import app.ConfPanel;
import app.ConferencePanel;
import app.HealthProperties;
import app.MeasurementTab;
import app.QuestionnareTab;
import app.Tabs;


import java.awt.Panel;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import app.MeasurementTab;

import app.Tabs;
import java.awt.Font;

public class MainPage {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HealthProperties healthProperties = new HealthProperties();
		healthProperties.loadProperties();
		frame = new JFrame();
		frame.setResizable(false);
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		int xSize = ((int) tk.getScreenSize().getWidth()) - 100;
//		int ySize = ((int) tk.getScreenSize().getWidth()) - 100;
//		frame.setPreferredSize(new Dimension(xSize, ySize));
		frame.setBounds(100, 100, 899, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 40, 435, 283, 0 };
		gbl_panel.rowHeights = new int[] { 189, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		JButton btnNewButton = new JButton("");
		

		btnNewButton.setIcon(new ImageIcon(MainPage.class.getResource("/pic/measure.jpg")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("");

		btnNewButton_1.setIcon(new ImageIcon(MainPage.class.getResource("/pic/question1.jpg")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);

		final Panel panel_2 = new Panel();
		frame.getContentPane().add(panel_2, BorderLayout.WEST);
						GridBagLayout gbl_panel_2 = new GridBagLayout();
						gbl_panel_2.columnWidths = new int[]{196, 193, 193, 193, 197, 0};
						gbl_panel_2.rowHeights = new int[] {30, 0, 30, 0};
						gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
						panel_2.setLayout(gbl_panel_2);
										
										JLabel lblNewLabel = new JLabel("Last Update");
										lblNewLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
										lblNewLabel.setForeground(new Color(30, 144, 255));
										lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
										GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
										gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
										gbc_lblNewLabel.gridx = 0;
										gbc_lblNewLabel.gridy = 0;
										panel_2.add(lblNewLabel, gbc_lblNewLabel);
										
										textField_1 = new JTextField();
										GridBagConstraints gbc_textField_1 = new GridBagConstraints();
										gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
										gbc_textField_1.insets = new Insets(0, 0, 5, 5);
										gbc_textField_1.gridx = 1;
										gbc_textField_1.gridy = 0;
										panel_2.add(textField_1, gbc_textField_1);
										textField_1.setColumns(10);
										
										JLabel lblNewLabel_1 = new JLabel("Last Update");
										lblNewLabel_1.setForeground(new Color(30, 144, 255));
										lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
										GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
										gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
										gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
										gbc_lblNewLabel_1.gridx = 2;
										gbc_lblNewLabel_1.gridy = 0;
										panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
										
										textField_2 = new JTextField();
										GridBagConstraints gbc_textField_2 = new GridBagConstraints();
										gbc_textField_2.insets = new Insets(0, 0, 5, 5);
										gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
										gbc_textField_2.gridx = 3;
										gbc_textField_2.gridy = 0;
										panel_2.add(textField_2, gbc_textField_2);
										textField_2.setColumns(10);
														
																JButton btnNewButton_4 = new JButton("");
																btnNewButton_4.setIcon(new ImageIcon(MainPage.class.getResource("/pic/video.jpg")));
																GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
																gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHEAST;
																gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
																gbc_btnNewButton_4.gridx = 0;
																gbc_btnNewButton_4.gridy = 1;
																panel_2.add(btnNewButton_4, gbc_btnNewButton_4);
																
																JButton button = new JButton("");
																button.setIcon(new ImageIcon(MainPage.class.getResource("/pic/history.jpg")));
																GridBagConstraints gbc_button = new GridBagConstraints();
																gbc_button.anchor = GridBagConstraints.EAST;
																gbc_button.insets = new Insets(0, 0, 5, 5);
																gbc_button.gridx = 1;
																gbc_button.gridy = 1;
																panel_2.add(button, gbc_button);
																
																JButton button_1 = new JButton("");
																button_1.setIcon(new ImageIcon(MainPage.class.getResource("/pic/bru.jpg")));
																GridBagConstraints gbc_button_1 = new GridBagConstraints();
																gbc_button_1.anchor = GridBagConstraints.EAST;
																gbc_button_1.insets = new Insets(0, 0, 5, 5);
																gbc_button_1.gridx = 2;
																gbc_button_1.gridy = 1;
																panel_2.add(button_1, gbc_button_1);
																
																JButton button_2 = new JButton("");
																button_2.setIcon(new ImageIcon(MainPage.class.getResource("/pic/info.jpg")));
																GridBagConstraints gbc_button_2 = new GridBagConstraints();
																gbc_button_2.anchor = GridBagConstraints.EAST;
																gbc_button_2.insets = new Insets(0, 0, 5, 5);
																gbc_button_2.gridx = 3;
																gbc_button_2.gridy = 1;
																panel_2.add(button_2, gbc_button_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				panel_2.setVisible(false);
				frame.repaint();
				frame.revalidate();
				
				//
			Measur measur=new Measur();
			
			frame.getContentPane().add(measur, BorderLayout.CENTER);
	
				//
				

				//MeasurementTab measurementTab = new MeasurementTab();
				//JPanel panel3 = measurementTab.createPanel("test");
				//panel3.setVisible(true);
				//panel3.setEnabled(true);
				//frame.getContentPane().add(panel3, BorderLayout.CENTER);
//				measurementTab.setVisible(true);
//				panel_3.setEnabled(true);
				frame.repaint();
				frame.revalidate();

			}
		});

	}

}
