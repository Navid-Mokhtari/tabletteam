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

import vitalsignals.Pulse;
import vitalsignals.Spirometry;

public class HttpsPostClient {

	private String URL = "https://iip3:iip3@128.39.147.213:8181/IipDevU4H/root/provider/publication/";

	/**
	 * @param args
	 *            the command line arguments
	 */
	public void SendPulseHttps(Pulse pulse) {
		// TODO code application logic here

		// HttpClient httpclient = new DefaultHttpClient();

		String infoId = "info:761538126";
		URL += infoId;
		HttpClient httpclient = getNewHttpClient();
		HttpPost httppost = new HttpPost(URL);
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
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		System.out.println("TESTING: " + response.getStatusLine());
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

	public void SendSpirometerHttps(Spirometry spirometry) {
		String infoId = "info:460491730";
		URL += infoId;
		HttpClient httpclient = getNewHttpClient();
		HttpPost httppost = new HttpPost(URL);
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
//			nameValuePairs.add(new BasicNameValuePair("time", spirometry
//					.getDate().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		System.out.println("TESTING: " + response.getStatusLine());
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
