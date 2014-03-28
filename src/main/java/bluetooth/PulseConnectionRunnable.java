package bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import vitalsignals.Pulse;
import app.Utilities;

import com.intel.bluetooth.BlueCoveImpl;
import com.intel.bluetooth.MicroeditionConnector;

public class PulseConnectionRunnable implements Runnable {
	private JLabel pulseValue, oxigenValue, timeLabel;
	private Pulse pulse;
	private JButton sendButton;
	// private JComponent measurementTab;
	StreamConnectionNotifier service;
	StreamConnection con;
	InputStream is;

	public StreamConnection getStreamConnection() {
		return con;
	}

	// Pulse
	public PulseConnectionRunnable(JLabel pulseValue, JLabel oxigenValue,
			JButton sendButton) {
		this.pulseValue = pulseValue;
		this.oxigenValue = oxigenValue;
		// this.timeLabel = time;
		this.sendButton = sendButton;
		// this.measurementTab = measurementTab;
	}

	public void run() {
		try {
			Utilities.pulseThread = this;
			pulse = startWaitingConnection();
			System.out.println("Trying to parse message");
			if (pulse.ParseMessage()) {
				System.out.println("Message parsed, trying to update GUI");
				updateGui();

			} else {
				System.out.println("Message was not parsed!");
			}
		} catch (Exception e) {
			System.out.println("We are here!" + e.toString());
		} finally {
			Utilities.pulseThread = null;
			Utilities utilities = new Utilities();
			Thread mainThread = utilities.getMainThread();
			System.out.println("Test=" + mainThread.getId());
			if (mainThread != null
					&& mainThread.getState() == State.TIMED_WAITING) {
				System.out.println("Trying to interrupt main Thread with ID="
						+ mainThread.getId());
				mainThread.interrupt();
			}
		}

	}

	private Pulse startWaitingConnection() {
		System.setProperty("bluecove.obex.timeout", "5000");
		System.setProperty("bluecove.connect.timeout", "5000");
		BlueCoveImpl.setConfigProperty("bluecove.obex.timeout", "5000");
		BlueCoveImpl.setConfigProperty("bluecove.connect.timeout", "5000");
		System.out.println(System.getProperty("bluecove.obex.timeout"));
		System.out.println(System.getProperty("bluecove.connect.timeout"));
		try {
			System.out.println("Device discoverable status:"
					+ LocalDevice.getLocalDevice().getDiscoverable());
			if (LocalDevice.getLocalDevice().getDiscoverable() != DiscoveryAgent.GIAC) {
				LocalDevice.getLocalDevice().setDiscoverable(
						DiscoveryAgent.GIAC);
			}
		} catch (BluetoothStateException e2) {
			System.out.println("Can't make device discoverable");
			e2.printStackTrace();
		}
		try {
			// service = (StreamConnectionNotifier) Connector
			// .open("btspp://localhost:"
			// + new UUID(0x1101).toString()
			// + ";name=pulseService;authenticate=false;encrypt=false;");
			service = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:"
							+ new UUID(0x1101).toString()
							+ ";name=pulseService;authenticate=false;encrypt=false;",
							MicroeditionConnector.READ_WRITE, true);
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
		Utilities.setConnection(service, con, is);
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
						pulseValue.setText(pulse.getPulse());
						oxigenValue.setText(pulse.getOxigen());
						sendButton.setEnabled(true);
						// timeLabel.setText(pulse.getCurrentDate().toString());
					}
					pulse = null;
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			System.out.println("It was interrupted" + e.toString());

		} finally {
			closeConnection();
		}
	}
}
