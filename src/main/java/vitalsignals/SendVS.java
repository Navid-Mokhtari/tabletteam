package vitalsignals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

public class SendVS {
	public StatusLine  SendTestSignal() throws ClientProtocolException {
		String urlCoordinates = "http://localhost:8080/testDynamicServlet/testServlet";
		String veraUrl = "http://test.crproject.net/InsertPatient.jsp";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(veraUrl);
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(2);
		valuePairs.add(new BasicNameValuePair("fname", "Test2"));
		valuePairs.add(new BasicNameValuePair("lname", "Test2"));
		valuePairs.add(new BasicNameValuePair("fnr", "123"));
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
		StatusLine statusLine=null;
		if (response != null) {
			 statusLine = response.getStatusLine();
			System.out.println(statusLine.getStatusCode());
		}
		return statusLine;
	}
}
