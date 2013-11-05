package bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import vitalsignals.Pulse;

public class PulseConnection implements Runnable {
	private JLabel pulseLabel, oxigenLabel, timeLabel;
	private Pulse pulse;

	public PulseConnection(JLabel pulseValue, JLabel oxigenValue, JLabel time) {
		this.pulseLabel = pulseValue;
		this.oxigenLabel = oxigenValue;
		this.timeLabel = time;
	}

	@Override
	public void run() {
		pulse = startWaitingConnection();
		if (pulse.ParseMessage()) {
			updateGui();
		}
	}

	private Pulse startWaitingConnection() {
		StreamConnectionNotifier service = null;
		try {
			service = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:" + new UUID(0x1101).toString()
							+ ";name=pulseService");
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}

		StreamConnection con = null;

		try {
			con = (StreamConnection) service.acceptAndOpen();
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}
		List<String> messageList = new ArrayList<String>();
		InputStream is = null;
		try {
			is = con.openInputStream();

			int lenght = 0;
			while (lenght < 22) {
				try {
					String temp = String.valueOf(is.read());
					messageList.add(temp);
					System.out.println(temp);
				} catch (IOException e) {
					System.out.println(e.toString());
				}
				lenght++;
			}
			System.out.println(messageList.toString());
		} catch (IOException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}
		try {
			con.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Input stream was unavalible");
		}
		return new Pulse(messageList);

	}

	public void updateGui() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if (pulse.getPulse() != null) {
					pulseLabel.setText(pulse.getPulse());
					oxigenLabel.setText(pulse.getOxigen());
					timeLabel.setText(pulse.getCurrentDate().toString());
				}
				pulse = null;
			}
		});
	}
}
