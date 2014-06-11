package app;

import iipintegration.HttpsPostClient;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

public class FlashPlayer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125208328959514400L;

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public FlashPlayer() throws IOException {
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);
		final JButton okButton = new JButton(
				currentLanguage.getString("submit"));

		setBounds(0, 0, 1366, 768);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 888, 0 };
		gridBagLayout.rowHeights = new int[] { 445, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;

			
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
