package bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.intel.bluetooth.BlueCoveImpl;
import com.intel.bluetooth.BluetoothConsts;
import com.intel.bluetooth.obex.BlueCoveOBEX;

import app.Utilities;
import vitalsignals.Pulse;

public class PulseConnectionRunnable implements Runnable {
	private JLabel pulseLabel, oxigenLabel, timeLabel;
	private Pulse pulse;
	private JComponent measurementTab;
	StreamConnectionNotifier service;
	StreamConnection con;
	InputStream is;

	public StreamConnection getStreamConnection() {
		return con;
	}

	// Pulse
	public PulseConnectionRunnable(JLabel pulseValue, JLabel oxigenValue,
			JLabel time, JComponent measurementTab) {
		this.pulseLabel = pulseValue;
		this.oxigenLabel = oxigenValue;
		this.timeLabel = time;
		this.measurementTab = measurementTab;
	}

	public void run() {
		try {
			pulse = startWaitingConnection();
			System.out.println("Trying to parse message");
			if (pulse.ParseMessage()) {
				System.out.println("Message parsed, trying to update GUI");
				updateGui();
				Utilities.disposeDialog(measurementTab);
			} else {
				System.out.println("Message was not parsed!");
			}
		} catch (Exception e) {
			System.out.println("We are here!" + e.toString());
		}

	}

	private Pulse startWaitingConnection() {
		try {
			LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
		} catch (BluetoothStateException e2) {
			System.out.println("Can't make device discoverable");
			e2.printStackTrace();
		}
		try {
			service = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:"
							+ new UUID(0x1101).toString()
							+ ";name=pulseService;authenticate=false;encrypt=false;");
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}

		try {
			System.out.println("Accepting and opening Stream Connection");
			con = (StreamConnection) service.acceptAndOpen();
			System.out.println(con.toString());
		} catch (Exception e1) {
			System.out.println("Some strange exeption");
		}
		List<String> messageList = new ArrayList<String>();
		try {
			System.out.println("Opening input stream");
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
		}
		System.out.print("Closing connection");
		closeConnection();
		return new Pulse(messageList);

	}

	private Pulse startConnection() {
		System.out.println("Trying to open stream connection");
		// StreamConnection conn = (StreamConnection) Connector
		// .open("btspp://001C050064D8:1");
		Utilities.setConnection(service, con, is);
		try {
			con = (StreamConnection) Connector.open("btspp://001C050064D8:1",
					1, true);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("Trying to open input stream");
		try {
			is = con.openInputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List<String> messageList = new ArrayList<String>();
		try {
			System.out.println("Opening input stream");
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
		}
		System.out.print("Closing connection");
		closeConnection();
		return new Pulse(messageList);
	}

	public void closeConnection() {
		try {
			con.close();
			is.close();
			service.close();
		} catch (Exception e) {
			con = null;
			is = null;
			service = null;
			System.out.println("Input stream was unavalible");
		}
	}

	public void updateGui() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				public void run() {
					System.out.println("Trying to update GUI");
					if (pulse.getPulse() != null) {
						pulseLabel.setText(pulse.getPulse());
						oxigenLabel.setText(pulse.getOxigen());
						timeLabel.setText(pulse.getCurrentDate().toString());
					}
					pulse = null;
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			System.out.println("It was interrupted");
			closeConnection();
		}
	}
}
