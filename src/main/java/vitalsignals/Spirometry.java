package vitalsignals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import no.uia.ehealth.app.Send;

public class Spirometry extends Measurements {
	private String deviceId, fev1, pef, fev1Best, pefBest, fev1Perc, pefPerc,
			greenZone, yellowZone, orangeZone, originalDate, swNumber,
			goodTest;

	private List<String> message = new ArrayList<String>();
	private String deviceLetter;
	private String messageId;
	private static final String TAG = "SpirometryClass";

	// Spirometry endpoint
	private String spirometryUrlString = "http://128.39.147.213:8080/IipDev/root/provider/publication/info:101985213";

	// private String spirometryUrlString =
	// "http://192.168.1.27:8080/IipDev/root/provider/publication/info:101985213";
	public Spirometry(List<String> message) {
		this.message = message;
	}

	public Spirometry() {
	}

	public boolean ParseMessage() {

		deviceLetter = message.get(0);
		messageId = sumValues(1, 2);
		if (messageId.equals("TD")) {
			try {
				System.out.println("Automatic singe test data received");
				deviceId = String.valueOf(sumValues(3, 12));
				setFev1(sumValues(13, 15));
				setPef(sumValues(16, 18));
				fev1Best = sumValues(19, 21);
				pefBest = sumValues(22, 24);
				fev1Perc = sumValues(25, 27);
				pefPerc = sumValues(28, 30);
				greenZone = sumValues(31, 33);
				yellowZone = sumValues(34, 36);
				orangeZone = sumValues(37, 39);
				originalDate = sumValues(40, 41) + "-" + sumValues(42, 43)
						+ "-" + sumValues(44, 45) + "-" + sumValues(46, 47)
						+ ":" + sumValues(48, 49) + ":" + sumValues(50, 51);
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yy-mm-dd-hh:mm:ss");

				try {
					setDate(dateFormat.parse(originalDate));
				} catch (ParseException e) {
					Calendar calendar = Calendar.getInstance();
					setDate(calendar.getTime());
					System.out.println("Couldn't parse Date" + getDate());
				}
				setGoodTest((message.get(52).equals("1")) ? "0" : "1");
				swNumber = sumValues(53, 55);
				return true;
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		} else {
			System.out.println("No single test data. Device identifier="
					+ deviceLetter);
			return false;
		}

	}

	private String sumValues(int startPoint, int endPoint) {
		String message = "";
		for (; startPoint <= endPoint; startPoint++) {
			message += this.message.get(startPoint);
		}
		return message;
	}

	// public boolean SendValues() {
	// String urlParameters = "ID=" + deviceId + "&" + "FEV1="
	// + getFev1().replaceFirst("^0+(?!$)", "") + "&" + "PEF="
	// + getPef().replaceFirst("^0+(?!$)", "") + "&"
	// + "FEV1PersonBest=" + fev1Best.replaceFirst("^0+(?!$)", "")
	// + "&" + "PEFPersonBest=" + pefBest.replaceFirst("^0+(?!$)", "")
	// + "&" + "FEV1Percentage="
	// + fev1Perc.replaceFirst("^0+(?!$)", "") + "&"
	// + "PEFPercentage=" + pefPerc.replaceFirst("^0+(?!$)", "") + "&"
	// + "GreenZone=" + greenZone.replaceFirst("^0+(?!$)", "") + "&"
	// + "YellowZone=" + yellowZone.replaceFirst("^0+(?!$)", "") + "&"
	// + "OrangeZone=" + orangeZone.replaceFirst("^0+(?!$)", "") + "&"
	// + "Time=" + originalDate + "&" + "GoodTest=" + goodTest + "&"
	// + "SWNumber=" + swNumber;
	//
	// try {
	// SendValues2 sendValues2 = new SendValues2();
	// Boolean successBoolean = sendValues2.HttpSend(spirometryUrlString,
	// urlParameters);
	// Boolean locationValue = sendValues2.SendLocation();
	// if (successBoolean && locationValue) {
	// return true;
	// }
	// return false;
	// } catch (Exception e) {
	// System.out.println(toString());
	// return false;
	// }
	// }

	public String getPef() {
		return pef;
	}

	private void setPef(String pef) {
		this.pef = pef;
	}

	public String getFev1() {
		return fev1;
	}

	private void setFev1(String fev1) {
		this.fev1 = fev1;
	}

	public String getGoodTest() {
		return (goodTest == "1") ? "Correct" : "Incorrect";
	}

	private void setGoodTest(String goodTest) {
		this.goodTest = goodTest;
	}

	public String getDeviceId() {
		return deviceId;
	}
}
