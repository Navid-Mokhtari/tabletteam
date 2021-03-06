package vitalsignals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base32;

public class Pulse extends Measurements implements Comparable<Pulse> {
	public Pulse() {
	}

	public Pulse(String pulse, String oxigen, Date time) {
		this.pulse = pulse;
		this.oxigen = oxigen;
		this.setDate(time);
	}

	public Pulse(String pulse, String oxigen) {
		this.pulse = pulse;
		this.oxigen = oxigen;
	}

	private String pulse;
	private String oxigen;
	private List<String> pulseMeasurements;
	// private String pulseUrlString =
	// "http://128.39.147.213:8080/IipDev/root/provider/publication/info:376600300";
	// private String oxigenUrlString =
	// "http://128.39.147.213:8080/IipDev/root/provider/publication/info:280046005";
	private String TAG = "Pulse";

	public Pulse(List<String> pulse) {
		pulseMeasurements = pulse;
	}

	public Boolean ParseMessage() {
		if (pulseMeasurements.size() % 22 == 0 && pulseMeasurements.size() != 0) {
			int lenght = pulseMeasurements.size();
			pulse = pulseMeasurements.get(lenght - 5);
			oxigen = pulseMeasurements.get(lenght - 3);
			setDate(getCurrentDate());
			if (pulse == "-1" || oxigen == "-1") {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public String getPulse() {
		return pulse;
	}

	public String getOxigen() {
		return oxigen;
	}

	public Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	@Override
	public int compareTo(Pulse o) {
		return getDate().compareTo(o.getDate());
	}

	@Override
	public String toString() {
		return "Pulse: " + pulse + ", Oxigen: " + oxigen + ", Time: "
				+ getDate();
	}
	// public Boolean SendValues() {
	// String urlPulseParameters = "pulse=" + pulse;
	// String urlOxiParameters = "spo2=" + oxigen;
	// try {
	// if (pulse == "0" || oxigen == "0") {
	// return false;
	// } else {
	// Calendar calendar = Calendar.getInstance();
	// date = calendar.getTime();
	// SendValues2 sendValues2 = new SendValues2();
	// boolean firstValue = sendValues2.HttpSend(pulseUrlString,
	// urlPulseParameters);
	// boolean secondValue = sendValues2.HttpSend(oxigenUrlString,
	// urlOxiParameters);
	// boolean thirdValue = sendValues2.SendLocation();
	// if (firstValue && secondValue&&thirdValue) {
	// return true;
	// }
	// return false;
	// }
	// } catch (Exception e) {
	// Log.e("Pulse.class:", "Send Value exception");
	// return false;
	// }
	// }
}
