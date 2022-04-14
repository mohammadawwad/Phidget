import com.phidget22.*;
import java.util.Scanner; //Required for Text Input
import java.io.IOException;

public class RFIDPhidget {

	public static void main(String[] args) throws Exception {
		RFID rfid0 = new RFID();

		rfid0.addTagListener(new RFIDTagListener() {
			public void onTag(RFIDTagEvent e) {
				System.out.println("Tag: " + e.getTag());
				System.out.println("Protocol: " + e.getProtocol().name());
				System.out.println("----------");
			}
		});

		rfid0.addTagLostListener(new RFIDTagLostListener() {
			public void onTagLost(RFIDTagLostEvent e) {
				System.out.println("Tag: " + e.getTag());
				System.out.println("Protocol: " + e.getProtocol().name());
				System.out.println("----------");
			}
		});

		rfid0.open(5000);

		//Wait until Enter has been pressed before exiting
		System.in.read();

		rfid0.close();
	}
}