/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iipintegration;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import app.HealthProperties;
import app.Utilities;
import vitalsignals.Pulse;
import vitalsignals.Spirometry;

public class HttpsPostClient {

	// Dafferianto's IIP
	// private String URL =
	// "https://iip3:iip3@128.39.147.213:8181/IipDevU4H/root/provider/publication/";
	// Devoteam IIP
	// private String URL =
	// "https://tablet_0001:tablet_0001@172.25.5.15:8181/IipDevU4H/root/provider/publication/";
	private String username = HealthProperties.getProperty("iipUsername");
	private String password = HealthProperties.getProperty("iipPassword");
	private String iipUrl = HealthProperties.getProperty("iipUrl");
	private String URL = "https://" + username + ":" + password + "@" + iipUrl;
	private String pulseChannel = HealthProperties.getProperty("pulseChannel");
	private String spirometerChannel = HealthProperties
			.getProperty("spirometerChannel");
	private String oxygenChannel = HealthProperties
			.getProperty("oxygenChannel");
	private String patientId = HealthProperties.getProperty("patientId");

	@SuppressWarnings("finally")
	public boolean SendPulseHttps(Pulse pulse) {
		boolean isSent = false;
		System.out.println("Trying to send pulse values");
		String infoId = pulseChannel;
		String pulseUrl = URL + infoId;
		HttpClient httpclient = getNewHttpClient();
		HttpPost httppost = new HttpPost(pulseUrl);
		HttpResponse response = null;
		try {
			// Date currentDate = new Date();
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			// "yyyy-MM-dd'T'HH:mm:ss");

			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					20);
			nameValuePairs
					.add(new BasicNameValuePair("pulse", pulse.getPulse()));
			nameValuePairs.add(new BasicNameValuePair("patientId", patientId));
			nameValuePairs.add(new BasicNameValuePair("dateTime", pulse
					.getDate().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			System.out.println("\nTesting sending Pulse: "
					+ response.getStatusLine());
			isSent = true;
		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
		}

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = response.getEntity().getContent();
			Document doc = db.parse(is);
			Element root2 = doc.getDocumentElement();
			printAllNodes(root2);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return isSent;
		}
	}

	public void SendSpirometerHttps(Spirometry spirometry) {
		String infoId = spirometerChannel;
		String spirometerUrl = URL + infoId;
		HttpClient httpclient = getNewHttpClient();
		HttpPost httppost = new HttpPost(spirometerUrl);
		HttpResponse response = null;
		try {
			// Date currentDate = new Date();
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			// "yyyy-MM-dd'T'HH:mm:ss");

			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					20);
			nameValuePairs.add(new BasicNameValuePair("FEV1", spirometry
					.getFev1()));
			nameValuePairs.add(new BasicNameValuePair("PEF", spirometry
					.getPef()));
			nameValuePairs.add(new BasicNameValuePair("patientId", "1234567"));
			// nameValuePairs.add(new BasicNameValuePair("time", spirometry
			// .getDate().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			System.out.println("Testing sending Spirometer: "
					+ response.getStatusLine());
		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = response.getEntity().getContent();
			Document doc = db.parse(is);
			Element root2 = doc.getDocumentElement();
			printAllNodes(root2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void SendOxygen(Pulse pulse) {
		System.err.println("Trying to send oxigen values");
		// String infoId = "info:761538126";
		String infoId = oxygenChannel;
		String oxygenUrl = URL + infoId;
		HttpClient httpclient = getNewHttpClient();
		HttpPost httppost = new HttpPost(oxygenUrl);
		HttpResponse response = null;
		try {
			// Date currentDate = new Date();
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			// "yyyy-MM-dd'T'HH:mm:ss");

			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					20);
			nameValuePairs
					.add(new BasicNameValuePair("spo2", pulse.getOxigen()));
			nameValuePairs.add(new BasicNameValuePair("patientId", patientId));
			nameValuePairs.add(new BasicNameValuePair("dateTime", pulse
					.getDate().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			System.out.println("Testing sending Oxygen: "
					+ response.getStatusLine());
		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = response.getEntity().getContent();
			Document doc = db.parse(is);
			Element root2 = doc.getDocumentElement();
			printAllNodes(root2);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			Utilities.disposeDialog(null);
		}

	}

	private static void printAllNodes(Node doc) {
		// System.out.println("Node: " + doc.getNodeName() + ", Value: " +
		// doc.getNodeValue());
		System.out.print("Tag: " + doc.getNodeName());
		NodeList childrenNodes = doc.getChildNodes();
		for (int i = 0; i < childrenNodes.getLength(); i++) {
			Node childNode = childrenNodes.item(i);
			if (childNode instanceof Element) {
				System.out.println();
				printAllNodes(childNode);
			} else {
				System.out.print(", Value: " + childNode.getNodeValue());
			}
		}
	}

	private HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 8080));
			registry.register(new Scheme("https", sf, 8181));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}
}
