package app;

import iipintegration.HttpsPostClient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.StatusLine;

import vitalsignals.Pulse;
import vitalsignals.SendVS;
import vitalsignals.Spirometry;
import bluetooth.PulseConnection;
import bluetooth.RemoteDevices;
import bluetooth.SpirometryConnection;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.org.apache.bcel.internal.generic.PUSH;

public class MeasurementTab extends JComponent implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3958650284297145802L;

	private JLabel pulseJLabel;
	private static JLabel pulseValue;
	private JLabel oxigenJLabel;
	private static JLabel oxigenValue;
	private JLabel timeLabel;
	private static JLabel timeValue;
	private JLabel statusJLabel;
	private static JLabel statusValue;

	private JButton measurePulse;
	private JButton sendPulse;

	private JLabel fev1JLabel;
	private static JLabel fev1Value;
	private JLabel pefJLabel;
	private static JLabel pefValue;
	private JLabel timeSpiroLabel;
	private static JLabel timeSpiroValue;

	private JButton measureSpiro;
	private JButton sendSpiro;

	SpirometryConnection spirometryConnection;

	public Component getView() {
		JComponent measurements = createPanel("My Measurements");
		measurements.setPreferredSize(new Dimension(600, 600));
		return measurements;
	}

	public JPanel createPanel(String title) {
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constrains = new GridBagConstraints();
		constrains.insets = new Insets(5, 5, 5, 5);
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.anchor = GridBagConstraints.FIRST_LINE_START;
		// constrains.weighty=1.0; //Vertical placement
		statusJLabel = new JLabel("Status:");
		constrains.gridx = 0;
		constrains.gridy = 0;
		panel.add(statusJLabel, constrains);

		statusValue = new JLabel("Connect device and make measurements");
		constrains.gridx = 1;
		constrains.gridy = 0;
		panel.add(statusValue, constrains);

		pulseJLabel = new JLabel("Pulse:");
		constrains.gridx = 0;
		constrains.gridy = 1;
		panel.add(pulseJLabel, constrains);

		pulseValue = new JLabel("85");
		constrains.gridx = 1;
		constrains.gridy = 1;
		panel.add(pulseValue, constrains);

		oxigenJLabel = new JLabel("Oxigen:");
		constrains.gridx = 0;
		constrains.gridy = 2;
		panel.add(oxigenJLabel, constrains);

		oxigenValue = new JLabel("98");
		constrains.gridx = 1;
		constrains.gridy = 2;
		panel.add(oxigenValue, constrains);

		timeLabel = new JLabel("Time:");
		constrains.gridx = 0;
		constrains.gridy = 3;
		panel.add(timeLabel, constrains);

		timeValue = new JLabel("12:30:13");
		constrains.gridx = 1;
		constrains.gridy = 3;
		panel.add(timeValue, constrains);

		measurePulse = new JButton("Measure Pulse");
		constrains.gridx = 0;
		constrains.gridy = 4;
		measurePulse.addActionListener(this);
		panel.add(measurePulse, constrains);

		sendPulse = new JButton("Send Pulse Values");
		constrains.gridx = 1;
		constrains.gridy = 4;
		sendPulse.addActionListener(this);
		panel.add(sendPulse, constrains);

		// Spirometer part
		fev1JLabel = new JLabel("Fev1:");
		constrains.gridx = 0;
		constrains.gridy = 5;
		panel.add(fev1JLabel, constrains);

		fev1Value = new JLabel("303");
		constrains.gridx = 1;
		constrains.gridy = 5;
		panel.add(fev1Value, constrains);

		pefJLabel = new JLabel("Pef:");
		constrains.gridx = 0;
		constrains.gridy = 6;
		panel.add(pefJLabel, constrains);

		pefValue = new JLabel("124");
		constrains.gridx = 1;
		constrains.gridy = 6;
		panel.add(pefValue, constrains);

		timeSpiroLabel = new JLabel("Time:");
		constrains.gridx = 0;
		constrains.gridy = 7;
		panel.add(timeSpiroLabel, constrains);

		timeSpiroValue = new JLabel("10:11:12");
		constrains.gridx = 1;
		constrains.gridy = 7;
		panel.add(timeSpiroValue, constrains);

		measureSpiro = new JButton("Measure Spirometer");
		constrains.gridx = 0;
		constrains.gridy = 8;
		measureSpiro.addActionListener(this);
		panel.add(measureSpiro, constrains);

		sendSpiro = new JButton("Send Spirometer Values");
		constrains.gridx = 1;
		constrains.gridy = 8;
		sendSpiro.addActionListener(this);
		panel.add(sendSpiro, constrains);

		setFonts();
		return panel;
	}

	private void setFonts() {
		Font Standartfont = new Font("Lucida Grande", Font.PLAIN, 20);
		statusJLabel.setFont(Standartfont);
		statusValue.setFont(Standartfont);
		pulseJLabel.setFont(Standartfont);
		pulseValue.setFont(Standartfont);
		oxigenJLabel.setFont(Standartfont);
		oxigenValue.setFont(Standartfont);
		timeLabel.setFont(Standartfont);
		timeValue.setFont(Standartfont);
		sendPulse.setFont(Standartfont);
		measurePulse.setFont(Standartfont);
		fev1JLabel.setFont(Standartfont);
		fev1Value.setFont(Standartfont);
		pefJLabel.setFont(Standartfont);
		pefValue.setFont(Standartfont);
		timeSpiroLabel.setFont(Standartfont);
		timeSpiroValue.setFont(Standartfont);
		sendSpiro.setFont(Standartfont);
		measureSpiro.setFont(Standartfont);

		// pulseValue.setForeground(Color.green);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendPulse) {
			// SendVS sendVS = new SendVS();
			// StatusLine statusLine = null;
			// try {
			// statusLine = sendVS.SendTestSignal(oxigenValue.getText(),
			// pulseValue.getText(),timeValue.getText());
			// statusValue.setText("Response:" + statusLine.getStatusCode());
			// } catch (ClientProtocolException e1) {
			// statusValue.setText("Was unable to send data");
			// }
			HttpsPostClient httpsPostClient = new HttpsPostClient();
			Pulse pulse = new Pulse(pulseValue.getText(),
					oxigenValue.getText(), timeValue.getText());
			httpsPostClient.SendPulseHttps(pulse);
		}
		if (e.getSource() == measurePulse) {
			PulseConnection pulseConnection = new PulseConnection(pulseValue,
					oxigenValue, timeValue);

			pulseConnection.run();
		}
		if (e.getSource() == measureSpiro) {
			spirometryConnection = new SpirometryConnection(fev1Value,
					pefValue, timeSpiroValue);
			spirometryConnection.measure();
			// CreateAndShowProgress();
		}
		if (e.getSource() == sendSpiro) {
			HttpsPostClient httpsPostClient = new HttpsPostClient();
			Spirometry spirometry = new Spirometry(fev1Value.getText(),
					pefValue.getText(), timeSpiroValue.getText());
			httpsPostClient.SendSpirometerHttps(spirometry);
			// SendVS sendVS = new SendVS();
			// StatusLine statusLine = null;
			// try {
			// statusLine = sendVS.SendTestSignal(oxigenValue.toString(),
			// pulseValue.toString());
			// statusValue.setText("Response:" + statusLine.getStatusCode());
			// } catch (ClientProtocolException e1) {
			// statusValue.setText("Was unable to send data");
			// }
			// RemoteDevices remoteDevices = new RemoteDevices();
			// remoteDevices.RemoveDeviceDiscovery();
			// remoteDevices.ServiceSearch();

		}

	}

	private void CreateAndShowProgress() {
		JFrame frame = new JFrame("ProgressBar");
		frame.setPreferredSize(new Dimension(100, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create and set up the content pane.
		JComponent newContentPane = new ProgressMonitor(this,
				spirometryConnection);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
