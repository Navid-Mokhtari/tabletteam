package vitalsignals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Pulse extends Measurements {
	public Pulse() {
	}

	public Pulse(String pulse, String oxigen, String time) {
		this.pulse = pulse;
		this.oxigen = oxigen;
		Date dateTime;
		try {
			dateTime = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
					.parse(time);
		} catch (ParseException e) {
			Calendar cal = Calendar.getInstance();
			dateTime = cal.getTime();
			e.printStackTrace();
		}
		this.setDate(dateTime);
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
		if (pulseMeasurements.size() == 22) {
			pulse = pulseMeasurements.get(17);
			oxigen = pulseMeasurements.get(19);
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
