package bluetooth;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

//Spirometer address: 1000E86D14FA
//Spirometer service COM1 found btspp://1000E86D14FA:1; authenticate=false;encrypt=false;master=false
public class RemoteDevices {
	// For device discovery
	public static final Vector<RemoteDevice> devicesDiscovered = new Vector();
	// For service search
	static final UUID HELLO_SERVICE = new UUID(0x1101);
	public static final Vector<String> serviceFound = new Vector();

	public void RemoveDeviceDiscovery() {

		devicesDiscovered.clear();
		final Object inquiryCompletedEvent = new Object();
		int[] attrIDs = new int[] { 0x0100 }; // Service name
		DiscoveryListener discoveryListener = new DiscoveryListener() {

			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
				System.out.println("Device " + btDevice.getBluetoothAddress()
						+ " found");
				devicesDiscovered.addElement(btDevice);
				try {
					System.out.println("     name "
							+ btDevice.getFriendlyName(false));
				} catch (IOException cantGetDeviceName) {
				}
			}

			public void inquiryCompleted(int discType) {
				System.out.println("Device Inquiry completed!");
				synchronized (inquiryCompletedEvent) {
					inquiryCompletedEvent.notifyAll();
				}
			}

			public void serviceSearchCompleted(int transID, int respCode) {
			}

			public void servicesDiscovered(int transID,
					ServiceRecord[] servRecord) {
			}
		};
		synchronized (inquiryCompletedEvent) {
			boolean started = false;
			try {
				started = LocalDevice.getLocalDevice().getDiscoveryAgent()
						.startInquiry(DiscoveryAgent.GIAC, discoveryListener);
			} catch (BluetoothStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (started) {
				System.out.println("wait for device inquiry to complete...");
				try {
					inquiryCompletedEvent.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(devicesDiscovered.size()
						+ " device(s) found");
			}
		}
	};

	public void ServiceSearch() {
		UUID[] searchUuidSet = new UUID[] { HELLO_SERVICE };
		int[] attrIDs = new int[] { 0x0100 }; // Service name
		RemoveDeviceDiscovery();
		serviceFound.clear();

		final Object serviceSearchCompletedEvent = new Object();

		DiscoveryListener listener = new DiscoveryListener() {
			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
			}

			public void inquiryCompleted(int discType) {
			}

			public void servicesDiscovered(int transID,
					ServiceRecord[] servRecord) {
				for (int i = 0; i < servRecord.length; i++) {
					String url = servRecord[i].getConnectionURL(
							ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
					if (url == null) {
						continue;
					}
					serviceFound.add(url);
					DataElement serviceName = servRecord[i]
							.getAttributeValue(0x0100);
					if (serviceName != null) {
						System.out.println("service " + serviceName.getValue()
								+ " found " + url);
					} else {
						System.out.println("service found " + url);
					}
				}
			}

			public void serviceSearchCompleted(int transID, int respCode) {
				System.out.println("service search completed!");

				synchronized (serviceSearchCompletedEvent) {
					serviceSearchCompletedEvent.notifyAll();
				}
			}
		};

		for (Enumeration en = devicesDiscovered.elements(); en
				.hasMoreElements();) {
			RemoteDevice btDevice = (RemoteDevice) en.nextElement();

			synchronized (serviceSearchCompletedEvent) {
				try {
					System.out.println("search services on "
							+ btDevice.getBluetoothAddress() + " "
							+ btDevice.getFriendlyName(false));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					LocalDevice
							.getLocalDevice()
							.getDiscoveryAgent()
							.searchServices(attrIDs, searchUuidSet, btDevice,
									listener);
				} catch (BluetoothStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					serviceSearchCompletedEvent.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
