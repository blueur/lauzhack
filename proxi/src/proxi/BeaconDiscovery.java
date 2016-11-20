package proxi;

import java.io.IOException;
import javax.bluetooth.*;
import java.util.ArrayList;

/**
 * Minimal Device Discovery example.
 */
public class BeaconDiscovery {

	private static ArrayList<RemoteDevice> devicesDiscovered, devicesMemorized;
	private static DiscoveryListener listener;

    private final static Object inquiryCompletedEvent = new Object();
    
    private int counter = 0;
	
	public BeaconDiscovery() {
		devicesDiscovered = new ArrayList<RemoteDevice>();
	    devicesDiscovered.clear();
	    devicesMemorized = new ArrayList<RemoteDevice>();
	    devicesMemorized.clear();
	
	    // Listener on bluetooth devices discovery events
	    listener = new DiscoveryListener() {
	        public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
	        	System.out.println("discovered");
                devicesDiscovered.add(btDevice);
	        	if (!devicesMemorized.contains(btDevice)) {
	                System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
	                devicesMemorized.add(btDevice);
	                try {
	                    System.out.println("     name " + btDevice.getFriendlyName(false));
	                } catch (IOException cantGetDeviceName) {
	                }
	        	}
	        }
	
	        public void inquiryCompleted(int discType) {
	            synchronized(inquiryCompletedEvent){
	            	System.out.println("completed " + counter++);
	            	for (RemoteDevice remoteDevice : devicesMemorized) {
						if (!devicesDiscovered.contains(remoteDevice)) {
							System.out.println("Lost device " + remoteDevice.getBluetoothAddress());
							devicesMemorized.remove(remoteDevice);
						} else
							System.out.println(remoteDevice.getBluetoothAddress() + " is still here.");
					}
	            	
	                inquiryCompletedEvent.notifyAll();
	            }
	        }
	
	        public void serviceSearchCompleted(int transID, int respCode) {
	        }
	
	        public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	        }
	    };
	}
	
	public void discover() throws InterruptedException, BluetoothStateException {
        synchronized(inquiryCompletedEvent) {
        	System.out.println("start " + counter);
        	devicesDiscovered.clear();
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
            if (started) {
                inquiryCompletedEvent.wait();
                try {
                	System.out.println("I'll relaunch that");
					discover();
				} catch (BluetoothStateException | InterruptedException e) {
					e.printStackTrace();
				}
            } else {
            	System.out.println("nope");
            }
        }
	}
}