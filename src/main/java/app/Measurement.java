package app;

import iipintegration.HttpsPostClient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import vitalsignals.Pulse;
import bluetooth.PulseConnectionRunnable;
import databaseaccess.DBConnection;

public class Measurement extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125208328959514400L;

	/**
	 * Create the dialog.
	 */
	public Measurement() {
		final JLabel pulseValue;
		final JLabel oxigenValue;
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);
		final JButton okButton = new JButton(
				currentLanguage.getString("submit"));

		setBounds(0, 0, 1366, 768);
		setTitle("UiA eHelse v" + HealthProperties.getProperty("appVersion"));
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 888, 0 };
		gridBagLayout.rowHeights = new int[] { 445, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
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
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(Measurement.class
						.getResource("/pic/measurment1.jpg")));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			// {
			// JLabel lblNewLabel_5 = new JLabel("                     ");
			// GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			// gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
			// gbc_lblNewLabel_5.gridx = 3;
			// gbc_lblNewLabel_5.gridy = 0;
			// panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			// }
			// {
			// JLabel lblNewLabel_2 = new JLabel("                      ");
			// GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			// gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			// gbc_lblNewLabel_2.gridx = 2;
			// gbc_lblNewLabel_2.gridy = 1;
			// panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			// }
			{
				JLabel lblNewLabel = new JLabel(
						currentLanguage.getString("firstInstruction"));
				lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
				lblNewLabel.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblWaitUntil = new JLabel(
						currentLanguage.getString("secondInstruction"));
				lblWaitUntil.setForeground(Color.WHITE);
				lblWaitUntil.setFont(new Font("Arial", Font.BOLD, 30));
				GridBagConstraints gbc_lblWaitUntil = new GridBagConstraints();
				gbc_lblWaitUntil.insets = new Insets(0, 0, 5, 5);
				gbc_lblWaitUntil.gridx = 1;
				gbc_lblWaitUntil.gridy = 4;
				panel.add(lblWaitUntil, gbc_lblWaitUntil);
			}
			// {
			// JLabel lblNewLabel_1 = new JLabel(
			// "                            see the measurments");
			// lblNewLabel_1.setForeground(Color.WHITE);
			// lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
			// GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			// gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			// gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			// gbc_lblNewLabel_1.gridx = 1;
			// gbc_lblNewLabel_1.gridy = 5;
			// panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			// }
			{
				pulseValue = new JLabel("                       ");
				pulseValue.setForeground(Color.WHITE);
				pulseValue.setFont(new Font("Arial", Font.BOLD, 30));
				pulseValue.setBorder(new TitledBorder(null, "",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_pulseValue = new GridBagConstraints();
				gbc_pulseValue.insets = new Insets(0, 0, 5, 5);
				gbc_pulseValue.gridx = 1;
				gbc_pulseValue.gridy = 11;
				panel.add(pulseValue, gbc_pulseValue);
			}
			{
				oxigenValue = new JLabel("                       ");
				oxigenValue.setForeground(Color.WHITE);
				oxigenValue.setFont(new Font("Arial", Font.BOLD, 30));
				oxigenValue.setBorder(new TitledBorder(null, "",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_oxigenValue = new GridBagConstraints();
				gbc_oxigenValue.insets = new Insets(0, 0, 5, 5);
				gbc_oxigenValue.gridx = 1;
				gbc_oxigenValue.gridy = 10;
				panel.add(oxigenValue, gbc_oxigenValue);
			}
			{
				JButton btnMeasure = new JButton(
						currentLanguage.getString("measure"));
				btnMeasure.setFont(new Font("Arial", Font.PLAIN, 40));
				btnMeasure.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						new Thread() {
							@Override
							public void run() {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										ClassLoader cldr = this.getClass()
												.getClassLoader();
										java.net.URL imageURL = cldr
												.getResource("Nonin.gif");
										ImageIcon noninImage = new ImageIcon(
												imageURL);

										String message = currentLanguage.getString("measureInstructions");
										String title = currentLanguage.getString("measuring");
										final JDialog dialog = new JDialog();
										dialog.setName("TemporaryBTDialog");
										dialog.setTitle(title);
										dialog.setUndecorated(false);
										JPanel panel = new JPanel();
										JLabel label = new JLabel(noninImage);
										JLabel messageLabel = new JLabel(
												message);
										messageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
										panel.add(label);
										panel.add(messageLabel);
										dialog.setLocation(410, 50);
										dialog.getContentPane().add(panel);
										dialog.pack();
										dialog.setVisible(true);
//										dialog.setLocationRelativeTo(rootPane);

									}
								});
								System.out.println("Main thread ID is "
										+ Thread.currentThread().getId());
								Thread thread = null;
								Utilities utilities = new Utilities();
								utilities.setMainThread(Thread.currentThread());
								if (Utilities.pulseThread == null) {
									PulseConnectionRunnable pc = new PulseConnectionRunnable(
											pulseValue, oxigenValue, okButton);
									thread = new Thread(pc);
									thread.start();
								}
								try {
									Thread.sleep(30000);
								} catch (InterruptedException e) {
									System.out
											.println("Main thread was woken up");
									e.printStackTrace();
								} finally {
									if (thread != null && thread.isAlive()) {
										thread.interrupt();
										thread = null;
										System.out.println("I am alive");
									}
									Utilities.disposeDialog(null);
									Utilities.closeConnection();
								}
							}
						}.start();
					}
				});
				GridBagConstraints gbc_btnMeasure = new GridBagConstraints();
				gbc_btnMeasure.insets = new Insets(0, 0, 5, 5);
				gbc_btnMeasure.gridx = 1;
				gbc_btnMeasure.gridy = 8;
				panel.add(btnMeasure, gbc_btnMeasure);
			}
			{
				JLabel lblNewLabel_3 = new JLabel(
						currentLanguage.getString("Dial.lblNewLabel_3.text")); //$NON-NLS-1$
				lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 30));
				lblNewLabel_3.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 10;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel(
						currentLanguage.getString("Dial.lblNewLabel_4.text"));
				lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 30));
				lblNewLabel_4.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 1;
				gbc_lblNewLabel_4.gridy = 11;
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
				// okButton = new JButton(currentLanguage.getString("submit"));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!pulseValue.getText().isEmpty()) {

							new Thread(new Runnable() {
								@Override
								public void run() {
									SwingUtilities.invokeLater(new Runnable() {
										@Override
										public void run() {
											ClassLoader cldr = this.getClass()
													.getClassLoader();
											java.net.URL imageURL = cldr
													.getResource("sending.gif");
											final ImageIcon sendingImage = new ImageIcon(
													imageURL);
											String message = "Trying to send measurements.\nIf you want to STOP measuring, \npress \"OK\".";
											String title = "Sending pulse measurements!";
											int messageType = MessageType.ERROR
													.ordinal();

											// JOptionPane.showMessageDialog(this,
											// message,
											// title,
											// messageType,
											// sendingImage);
											// Then we display the dialog on
											// that frame
											final JDialog dialog = new JDialog();
											dialog.setPreferredSize(new Dimension(
													380, 170));
											dialog.setName("TemporarySending");
											dialog.setTitle(currentLanguage
													.getString("sendingMeasurement"));
											dialog.setUndecorated(false);
											JPanel panel = new JPanel();
											final JLabel label = new JLabel(
													sendingImage);
											sendingImage
													.setImageObserver(label);
											panel.add(label);
											dialog.getContentPane().add(panel);
											dialog.pack();
											// Public method to center the
											// dialog after calling
											// pack()
											dialog.setLocationRelativeTo(rootPane);
											dialog.setVisible(true);
										}
									});
									HttpsPostClient httpsPostClient = new HttpsPostClient();
									Pulse pulse = new Pulse(pulseValue
											.getText(), oxigenValue.getText());
									// sendPulse.setText("Sending measurements...");
									httpsPostClient.SendPulseHttps(pulse);
									httpsPostClient.SendOxygen(pulse);
									DBConnection dbConnection = new DBConnection();
									try {
										// dbConnection.savePulse(pulse);
									} catch (Exception e1) {
										// TODO Auto-generated catch
										// block
										e1.printStackTrace();
									}
									//TODO insert data change to the main window!
									dispose();
								}

							}).start();

						}
					}
				});
				okButton.setForeground(new Color(30, 144, 255));
				okButton.setFont(new Font("Tahoma", Font.BOLD, 40));
				okButton.setActionCommand("OK");
				okButton.setEnabled(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(
						currentLanguage.getString("cancel"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setForeground(new Color(30, 144, 255));
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 40));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
