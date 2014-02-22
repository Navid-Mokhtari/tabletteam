package app;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.TitledBorder;

import bluetooth.PulseConnectionRunnable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Dial extends JDialog {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dial dialog = new Dial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dial() {
		
		setBounds(0, 0, 1366, 768);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{888, 0};
		gridBagLayout.rowHeights = new int[]{445, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(30, 144, 255));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(Dial.class.getResource("/pic/measure.jpg")));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 0);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblNewLabel = new JLabel(". Make sure the instrunment is attached well\r\n");
				lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
				lblNewLabel.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblWaitUntil = new JLabel("  . Wait until the measurment bar is full and you");
				lblWaitUntil.setForeground(Color.WHITE);
				lblWaitUntil.setFont(new Font("Arial", Font.BOLD, 18));
				GridBagConstraints gbc_lblWaitUntil = new GridBagConstraints();
				gbc_lblWaitUntil.insets = new Insets(0, 0, 5, 0);
				gbc_lblWaitUntil.gridx = 0;
				gbc_lblWaitUntil.gridy = 4;
				panel.add(lblWaitUntil, gbc_lblWaitUntil);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("    see the measurments");
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 5;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JButton btnMeasure = new JButton("Measure");
				btnMeasure.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ClassLoader cldr = this.getClass().getClassLoader();
						java.net.URL imageURL = cldr.getResource("Nonin.gif");
						ImageIcon noninImage = new ImageIcon(imageURL);
//						PulseConnectionRunnable pc = new PulseConnectionRunnable(
//								pulseValue, oxigenValue, timeValue, this);
//						Thread thread = new Thread(pc);
						String message = "Trying to get measurements.";
						String title = "Measure pulse";
						final JDialog dialog = new JDialog();
						dialog.setTitle(title);
						dialog.setUndecorated(false);
						JPanel panel = new JPanel();
						final JLabel label = new JLabel(noninImage);
						final JLabel messageLabel = new JLabel(message);
						panel.add(label);
						panel.add(messageLabel);
						dialog.getContentPane().add(panel);
						dialog.pack();
						// Public method to center the dialog after calling pack()
//						dialog.setLocationRelativeTo(this.getParent());
						dialog.setVisible(true);
//						thread.start();
						// if (thread.isAlive()) {
						// // Utilities.closeConnection();
						// System.out.println("Nothing yet implemented here");
						// System.out.println("Connection with button was closed!");
						// } else {
						// System.err
						// .println("Thread cannot be interrupted, because it is not alive");
						// }
					}
				});
				GridBagConstraints gbc_btnMeasure = new GridBagConstraints();
				gbc_btnMeasure.insets = new Insets(0, 0, 5, 0);
				gbc_btnMeasure.gridx = 0;
				gbc_btnMeasure.gridy = 7;
				panel.add(btnMeasure, gbc_btnMeasure);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("        Spo2        ");
				lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
				lblNewLabel_3.setForeground(Color.WHITE);
				lblNewLabel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 9;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("        Pulse        ");
				lblNewLabel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 18));
				lblNewLabel_4.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_4.gridx = 0;
				gbc_lblNewLabel_4.gridy = 10;
				panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(30, 144, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("Send");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				okButton.setForeground(new Color(30, 144, 255));
				okButton.setFont(new Font("Arial", Font.BOLD, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setForeground(new Color(30, 144, 255));
				cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}