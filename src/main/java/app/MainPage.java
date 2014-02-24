package app;

import app.CatQuestionDial;
import app.HealthProperties;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Toolkit;

public class MainPage {

	private JFrame frmUiaEhelse;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmUiaEhelse.setVisible(true);
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
		frmUiaEhelse = new JFrame();
		frmUiaEhelse.setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainPage.class.getResource("/pic/Doctor-icon2.png")));
		frmUiaEhelse.setTitle("UiA  eHelse");
		frmUiaEhelse.setResizable(false);
		// Toolkit tk = Toolkit.getDefaultToolkit();
		// int xSize = ((int) tk.getScreenSize().getWidth()) - 100;
		// int ySize = ((int) tk.getScreenSize().getWidth()) - 100;
		// frame.setPreferredSize(new Dimension(xSize, ySize));
		frmUiaEhelse.setBounds(0, 0, 1366, 768);
		frmUiaEhelse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Panel panel = new Panel();
		frmUiaEhelse.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 589, 379, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 305, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnNewButton = new JButton("");
		btnNewButton.setMargin(new Insets(4, 4, 2, 4));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dial dial = new Dial();
				dial.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Final-measurment.jpg")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setMargin(new Insets(4, 4, 4, 4));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				QuestionDial questiondial = new QuestionDial();
				questiondial.setVisible(true);

			}
		});
		btnNewButton_2.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Question.JPG")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_1 = new JButton("");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setMargin(new Insets(4, 4, 4, 4));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CatQuestionDial catquestiondial = new CatQuestionDial();
				catquestiondial.setVisible(true);

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/cat1.jpg")));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Doctor-icon2.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		JLabel lblNewLabel_3 = new JLabel("              ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		final Panel panel_2 = new Panel();
		frmUiaEhelse.getContentPane().add(panel_2, BorderLayout.WEST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 196, 193, 193, 193, 197, 0 };
		gbl_panel_2.rowHeights = new int[] { 30, 0, 30, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setMargin(new Insets(4, 12, 4, 4));
		btnNewButton_3.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Final-History.jpg")));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 1;
		panel_2.add(btnNewButton_3, gbc_btnNewButton_3);

		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setMargin(new Insets(4, 4, 4, 4));
		button_1.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Final-Bruk.jpg")));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		panel_2.add(button_1, gbc_button_1);

		JButton button = new JButton("");
		button.setEnabled(false);
		button.setMargin(new Insets(4, 4, 4, 4));
		button.setIcon(new ImageIcon(MainPage.class
				.getResource("/pic/Final-Info.jpg")));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		panel_2.add(button, gbc_button);

		JLabel lblNewLabel_1 = new JLabel("Connection Status :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Last Measurement Update :");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblUiaEhelse = new JLabel("  \u00A9 UiA, eHelse, 2013  \r\n ");
		lblUiaEhelse.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lblUiaEhelse.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		GridBagConstraints gbc_lblUiaEhelse = new GridBagConstraints();
		gbc_lblUiaEhelse.insets = new Insets(0, 0, 5, 5);
		gbc_lblUiaEhelse.gridx = 1;
		gbc_lblUiaEhelse.gridy = 4;
		panel_2.add(lblUiaEhelse, gbc_lblUiaEhelse);

		JLabel lblNewLabel = new JLabel("Last Update");
		lblNewLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 6;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 6;
		panel_2.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

	}

}
