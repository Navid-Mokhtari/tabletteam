package bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import vitalsignals.Spirometry;

public class SpirometryConnection {
	private JLabel fev1Value, pefValue, timeSpiroValue;
	private Spirometry spirometry;
	StreamConnectionNotifier service;
	StreamConnection con;
	InputStream is;

	public SpirometryConnection(JLabel fev1Value, JLabel pefValue,
			JLabel timeSpiroValue) {
		this.fev1Value = fev1Value;
		this.pefValue = pefValue;
		this.timeSpiroValue = timeSpiroValue;
	}

	public void run() {
		System.out.println("1");
		// RemoteDevices remoteDevices = new RemoteDevices();
		// remoteDevices.RemoveDeviceDiscovery();
		// remoteDevices.ServiceSearch();
		spirometry = startConnection();
		// spirometry = startWaitingConnection();
		System.out.println(getClass().getName()
				+ "Trying to parse spirometry data");
		if (spirometry.ParseMessage()) {
			updateGui();
		}

	}

	private Spirometry startWaitingConnection() {
		// try {
		// LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
		// } catch (BluetoothStateException e2) {
		// System.out.println("Can't make device discoverable");
		// e2.printStackTrace();
		// }
		try {

			service = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:"
							+ new UUID(0x1101).toString()
							+ ";name=spirometryService;authenticate=false;encrypt=false;");
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}

		try {
			System.out.println("Accepting and opening Stream Connection");
			con = (StreamConnection) service.acceptAndOpen();
			System.out.println(con.toString());
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}
		List<String> messageList = new ArrayList<String>();
		try {
			System.out.println("Opening input stream");
			is = con.openInputStream();
			int lenght = 0;
			int data = is.read();
			if (data == 21) {
				System.out.println("Negative acknowledgement character " + data
						+ " Closing connection");
			} else {
				System.out
						.println("Possitive acknowledgement character (WUNDERBA) "
								+ data);
				String message = "";
				while (data != 3) {
					data = is.read();
					messageList.add(String.valueOf((char) data));
					message += String.valueOf((char) data) + " ";
				}
				System.out.println(messageList.toString());
			}
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}
		System.out.print("Closing connection");
		closeConnection();
		return new Spirometry(messageList);

	}

	private Spirometry startConnection() {
		List<String> messageList = new ArrayList<String>();
		try {
			System.out.println("Trying to open stream connection");
			// StreamConnection conn = (StreamConnection) Connector
			// .open("btspp://1000E86D14FA:1");
			con = (StreamConnection) Connector.open("btspp://1000E86D14FA:1",
					1, true);
			System.out.println("Trying to open input stream");
			is = con.openInputStream();
			int lenght = 0;
			int data = is.read();
			if (data == 21) {
				System.out.println("Negative acknowledgement character " + data
						+ " Closing connection");
			} else {
				System.out
						.println("Possitive acknowledgement character (WUNDERBA) "
								+ data);
				String message = "";
				while (data != 3) {
					data = is.read();
					messageList.add(String.valueOf((char) data));
					message += String.valueOf((char) data) + " ";
				}
				System.out.println(messageList.toString());
			}
		} catch (BluetoothConnectionException e) {
			System.out.println("No response, trying again");
			System.out.println(e);
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeConnection();
		return new Spirometry(messageList);
	}

	private void closeConnection() {
		try {
			con.close();
			is.close();
			// service.close();
		} catch (IOException e) {
			con = null;
			is = null;
			// service = null;
			System.out.println("Input stream was unavalible");
		}
	}

	public void updateGui() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if (spirometry.getFev1() != null) {
					pefValue.setText(spirometry.getPef());
					fev1Value.setText(spirometry.getFev1());
					timeSpiroValue.setText(spirometry.getDate().toString());
				}
				spirometry = null;
			}
		});
	}
}
