package vitalsignals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import sun.util.calendar.BaseCalendar.Date;

public class SendVS {
	public StatusLine SendTestSignal(String oxi, String pulse, String dateTime)
			throws ClientProtocolException {
		oxi = oxi.isEmpty() ? "100" : oxi;
		pulse = pulse.isEmpty() ? "100" : pulse;
		String urlCoordinates = "http://localhost:8080/testDynamicServlet/testServlet";
		String veraUrl = "http://telehomecare.crproject.net/addOxymetry.jsp";
		String veraSpiroUrl = "http://telehomecare.crproject.net/addaddSpirometry.jsp";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(veraUrl);
		// HttpPost httppost = new HttpPost(veraSpiroUrl);
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(2);
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		String time = String.valueOf(date.getHours()) + ":"
				+ String.valueOf(date.getMinutes()) + ":"
				+ String.valueOf(date.getSeconds());
		System.out.println(time);
		// for Oxigen
		valuePairs.add(new BasicNameValuePair("patientId", "1234"));
		valuePairs.add(new BasicNameValuePair("equipmentId", "4444444"));
		valuePairs.add(new BasicNameValuePair("tabletId", "1234"));
		valuePairs.add(new BasicNameValuePair("oxy", oxi));
		valuePairs.add(new BasicNameValuePair("pulse", pulse));
		valuePairs.add(new BasicNameValuePair("datetime", time));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(valuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StatusLine statusLine = null;
		if (response != null) {
			statusLine = response.getStatusLine();
			System.out.println(statusLine.toString());
			System.out.println(statusLine.getStatusCode());
		}
		return statusLine;
	}
}
