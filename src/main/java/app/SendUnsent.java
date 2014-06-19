package app;

import iipintegration.MySSLSocketFactory;

import java.io.IOException;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class SendUnsent {
	
	
	public SendUnsent() {
		
	}
	public void sendUnsentValues() {
		Connection connect = null;
		String dbName = app.HealthProperties.getProperty("dbName");
		String dbUsername = app.HealthProperties.getProperty("dbUsername");
		String dbPassword = app.HealthProperties.getProperty("dbPassword");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
	
			// Getting the EHRIDs for unsent contents
			Statement readUnsentFromEHR = connect.createStatement();
			readUnsentFromEHR
					.executeQuery("SELECT * FROM eHealthDB.EHR;");
			
			ResultSet unsentResult = readUnsentFromEHR.getResultSet();
			unsentResult.next();
			// Getting first EHRID in table
			Integer firstUnsentEHRID = unsentResult.getInt("EHRID");
			// Getting last EHRID in table
			Statement readUnsentFromEHRLast = connect.createStatement();
			readUnsentFromEHRLast
			.executeQuery("SELECT * FROM eHealthDB.EHR ORDER BY EHRID DESC LIMIT 1;");
			ResultSet lastEHR = readUnsentFromEHRLast.getResultSet();
			lastEHR.next();
			Integer lastUnsentEHRID = lastEHR.getInt("EHRID");
			
			for (int i = firstUnsentEHRID; i < lastUnsentEHRID + 1; i++) {
				if (unsentResult.getInt("conceptIDFromConcept") == 1 && unsentResult.getInt("EHRSentStatus") == 0) {
					System.out.println("I am sending pulse");
					
					// Getting the EHRIDs for unsent contents
					Statement readUnsentContent = connect.createStatement();
					readUnsentContent
							.executeQuery("SELECT * FROM eHealthDB.EHRContent where EHRIDFromEHR='" + i + "';");
					
					
					Statement readCorrEHRID = connect.createStatement();
					readCorrEHRID
							.executeQuery("SELECT * FROM eHealthDB.EHR WHERE EHRID='" + i + "';");
					ResultSet readCorrEHRIDResult = readCorrEHRID.getResultSet();
					readCorrEHRIDResult.next();
					
					ResultSet unsentContentResult = readUnsentContent.getResultSet();
					
					// New sql statement
					String patientID = readCorrEHRIDResult.getString("pasientID");
					String dateTime = readCorrEHRIDResult.getString("EHRDateTime");
					
					unsentContentResult.next();
					String unsentID1 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV1 = unsentContentResult.getString("parameterValue");
					
//					unsentContentResult.next();
//					String unsentID2 = unsentContentResult.getString("parameterIDFromConceptParameters");
//					String unsentPV2 = "0";
//					unsentPV2 = unsentContentResult.getString("parameterValue");
//					
//					unsentContentResult.next();
//					String unsentID3 = unsentContentResult.getString("parameterIDFromConceptParameters");
//					String unsentPV3 = "0";
//					unsentPV3 = unsentContentResult.getString("parameterValue");
					
					readUnsentContent.close();
					unsentContentResult.close();
					readCorrEHRID.close();
					readCorrEHRIDResult.close();
			
					System.out.println(unsentID1 + " " + unsentPV1);
//					System.out.println(unsentID2 + " " + unsentPV2);
//					System.out.println(unsentID3 + " " + unsentPV3);
					
					// HTTP
					String username = HealthProperties.getProperty("iipUsername");
					String password = HealthProperties.getProperty("iipPassword");
					String iipUrl = HealthProperties.getProperty("iipUrl");
					String pulseChannel = HealthProperties
							.getProperty("pulseChannel");
					String URL = "https://" + username + ":" + password + "@" + iipUrl
							+ pulseChannel;
					Integer statusSent = 0;

					HttpClient httpclient = getNewHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					HttpResponse response = null;

					// Array to send to IIP

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("pulse", unsentPV1));
//					params.add(new BasicNameValuePair("pmdid", unsentPV2));
//					params.add(new BasicNameValuePair("pmdBatteryLevel", unsentPV3));
					params.add(new BasicNameValuePair("patientId", patientID));
					params.add(new BasicNameValuePair("dateTime", dateTime));
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						response = httpclient.execute(httpPost);
						System.out.println("\nSending the unsent pulse with EHRID " + i +": "
								+ response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {
							statusSent = 1;
						} else
							statusSent = 0;

					} catch (ClientProtocolException e1) {
						System.out.println(e1.toString());
					} catch (IOException e1) {
						System.out.println(e1.toString());
					}
					
					if (statusSent == 1) {
						Statement updateTable = connect.createStatement();
						updateTable
								.executeUpdate("UPDATE `eHealthDB`.`EHR` SET `EHRSentStatus`='1' WHERE `EHRID`='" + i + "';");
					}
					
				} else if (unsentResult.getInt("conceptIDFromConcept") == 2 && unsentResult.getInt("EHRSentStatus") == 0) {
					System.out.println("I am sending SpO2");
					
					// Getting the EHRIDs for unsent contents
					Statement readUnsentContent = connect.createStatement();
					readUnsentContent
							.executeQuery("SELECT * FROM eHealthDB.EHRContent where EHRIDFromEHR='" + i + "';");
					ResultSet unsentContentResult = readUnsentContent.getResultSet();
					
					Statement readCorrEHRID = connect.createStatement();
					readCorrEHRID
							.executeQuery("SELECT * FROM eHealthDB.EHR WHERE EHRID='" + i + "';");
					ResultSet readCorrEHRIDResult = readCorrEHRID.getResultSet();
					readCorrEHRIDResult.next();					
					
					// New sql statement
					String patientID = readCorrEHRIDResult.getString("pasientID");
					String dateTime = readCorrEHRIDResult.getString("EHRDateTime");
					
					unsentContentResult.next();
					String unsentID4 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV4 = unsentContentResult.getString("parameterValue");
					
//					unsentContentResult.next();
//					String unsentID5 = unsentContentResult.getString("parameterIDFromConceptParameters");
//					String unsentPV5 = "0";
//					unsentPV5 = unsentContentResult.getString("parameterValue");
//					
//					unsentContentResult.next();
//					String unsentID6 = unsentContentResult.getString("parameterIDFromConceptParameters");
//					String unsentPV6 = "0";
//					unsentPV6 = unsentContentResult.getString("parameterValue");
					
					readUnsentContent.close();
					unsentContentResult.close();
					readCorrEHRID.close();
					readCorrEHRIDResult.close();
			
					System.out.println(unsentID4 + " " + unsentPV4);
//					System.out.println(unsentID5 + " " + unsentPV5);
//					System.out.println(unsentID6 + " " + unsentPV6);
					
					// HTTP
					String username = HealthProperties.getProperty("iipUsername");
					String password = HealthProperties.getProperty("iipPassword");
					String iipUrl = HealthProperties.getProperty("iipUrl");
					String oxygenChannel = HealthProperties
							.getProperty("oxygenChannel");
					String URL = "https://" + username + ":" + password + "@" + iipUrl
							+ oxygenChannel;
					Integer statusSent = 0;

					HttpClient httpclient = getNewHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					HttpResponse response = null;

					// Array to send to IIP

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("spo2", unsentPV4));
//					params.add(new BasicNameValuePair("pmdid", unsentPV5));
//					params.add(new BasicNameValuePair("pmdBatteryLevel", unsentPV6));
					params.add(new BasicNameValuePair("patientId", patientID));
					params.add(new BasicNameValuePair("dateTime", dateTime));
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						response = httpclient.execute(httpPost);
						System.out.println("\nSending the unsent Spo2 with EHRID " + i +": "
								+ response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {
							statusSent = 1;
						} else
							statusSent = 0;

					} catch (ClientProtocolException e1) {
						System.out.println(e1.toString());
					} catch (IOException e1) {
						System.out.println(e1.toString());
					}
					
					if (statusSent == 1) {
						Statement updateTable = connect.createStatement();
						updateTable
								.executeUpdate("UPDATE `eHealthDB`.`EHR` SET `EHRSentStatus`='1' WHERE `EHRID`='" + i + "';");
					}
					
				} else if (unsentResult.getInt("conceptIDFromConcept") == 3 && unsentResult.getInt("EHRSentStatus") == 0) {
					System.out.println("I am sending Spirometer");
					
					// Getting the EHRIDs for unsent contents
					Statement readUnsentContent = connect.createStatement();
					readUnsentContent
							.executeQuery("SELECT * FROM eHealthDB.EHRContent where EHRIDFromEHR='" + i + "';");
					ResultSet unsentContentResult = readUnsentContent.getResultSet();
					
					Statement readCorrEHRID = connect.createStatement();
					readCorrEHRID
							.executeQuery("SELECT * FROM eHealthDB.EHR WHERE EHRID='" + i + "';");
					ResultSet readCorrEHRIDResult = readCorrEHRID.getResultSet();
					readCorrEHRIDResult.next();					
					
					// New sql statement
					String patientID = readCorrEHRIDResult.getString("pasientID");
					String dateTime = readCorrEHRIDResult.getString("EHRDateTime");
					
					unsentContentResult.next();
					String unsentID13 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV13 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID16 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV16 = unsentContentResult.getString("parameterValue");
										
					readUnsentContent.close();
					unsentContentResult.close();
					readCorrEHRID.close();
					readCorrEHRIDResult.close();
			
					System.out.println(unsentID13 + " " + unsentPV13);
					System.out.println(unsentID16 + " " + unsentPV16);
					
					// HTTP
					String username = HealthProperties.getProperty("iipUsername");
					String password = HealthProperties.getProperty("iipPassword");
					String iipUrl = HealthProperties.getProperty("iipUrl");
					String spirometerChannel = HealthProperties
							.getProperty("spirometerChannel");
					String URL = "https://" + username + ":" + password + "@" + iipUrl
							+ spirometerChannel;
					Integer statusSent = 0;

					HttpClient httpclient = getNewHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					HttpResponse response = null;

					// Array to send to IIP

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("FEV1", unsentPV13));
					params.add(new BasicNameValuePair("PEF", unsentPV16));
					params.add(new BasicNameValuePair("patientId", patientID));
					params.add(new BasicNameValuePair("dateTime", dateTime));
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						response = httpclient.execute(httpPost);
						System.out.println("\nSending the unsent pulse questionnaire with EHRID " + i +": "
								+ response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {
							statusSent = 1;
						} else
							statusSent = 0;

					} catch (ClientProtocolException e1) {
						System.out.println(e1.toString());
					} catch (IOException e1) {
						System.out.println(e1.toString());
					}
					
					if (statusSent == 1) {
						Statement updateTable = connect.createStatement();
						updateTable
								.executeUpdate("UPDATE `eHealthDB`.`EHR` SET `EHRSentStatus`='1' WHERE `EHRID`='" + i + "';");
					}
					
					
				} else if (unsentResult.getInt("conceptIDFromConcept") == 4 && unsentResult.getInt("EHRSentStatus") == 0) {
					System.out.println("I am sending daily");
					
					// Getting the EHRIDs for unsent contents
					Statement readUnsentContent = connect.createStatement();
					readUnsentContent
							.executeQuery("SELECT * FROM eHealthDB.EHRContent where EHRIDFromEHR='" + i + "';");
					ResultSet unsentContentResult = readUnsentContent.getResultSet();
					
					Statement readCorrEHRID = connect.createStatement();
					readCorrEHRID
							.executeQuery("SELECT * FROM eHealthDB.EHR WHERE EHRID='" + i + "';");
					ResultSet readCorrEHRIDResult = readCorrEHRID.getResultSet();
					readCorrEHRIDResult.next();					
					
					// New sql statement
					String patientID = readCorrEHRIDResult.getString("pasientID");
					String dateTime = readCorrEHRIDResult.getString("EHRDateTime");
					
					unsentContentResult.next();
					String unsentID19 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV19 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID20 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV20 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID21 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV21 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID22 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV22 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID23 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV23 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID24 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV24 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID25 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV25 = unsentContentResult.getString("parameterValue");
			
					readUnsentContent.close();
					unsentContentResult.close();
					readCorrEHRID.close();
					readCorrEHRIDResult.close();
			
					System.out.println(unsentID19 + " " + unsentPV19);
					System.out.println(unsentID20 + " " + unsentPV20);
					System.out.println(unsentID21 + " " + unsentPV21);
					System.out.println(unsentID22 + " " + unsentPV22);
					System.out.println(unsentID23 + " " + unsentPV23);
					System.out.println(unsentID24 + " " + unsentPV24);
					System.out.println(unsentID25 + " " + unsentPV25);
					
					// HTTP
					String username = HealthProperties.getProperty("iipUsername");
					String password = HealthProperties.getProperty("iipPassword");
					String iipUrl = HealthProperties.getProperty("iipUrl");
					String questionnaireChannel = HealthProperties
							.getProperty("questionnaireChannel");
					String URL = "https://" + username + ":" + password + "@" + iipUrl
							+ questionnaireChannel;
					Integer statusDaily = 0;

					HttpClient httpclient = getNewHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					HttpResponse response = null;

					// Array to send to IIP

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("q_1", unsentPV19));
					params.add(new BasicNameValuePair("q_2", unsentPV20));
					params.add(new BasicNameValuePair("q_3", unsentPV21));
					params.add(new BasicNameValuePair("q_4", unsentPV21));
					params.add(new BasicNameValuePair("q_5", unsentPV23));
					params.add(new BasicNameValuePair("q_6", unsentPV24));
					params.add(new BasicNameValuePair("q_7", unsentPV25));
					params.add(new BasicNameValuePair("patientId", patientID));
					params.add(new BasicNameValuePair("dateTime", dateTime));
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						response = httpclient.execute(httpPost);
						System.out.println("\nSending the unsent daily questionnaire with EHRID " + i +": "
								+ response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {
							statusDaily = 1;
						} else
							statusDaily = 0;

					} catch (ClientProtocolException e1) {
						System.out.println(e1.toString());
					} catch (IOException e1) {
						System.out.println(e1.toString());
					}
					
					if (statusDaily == 1) {
						Statement updateTable = connect.createStatement();
						updateTable
								.executeUpdate("UPDATE `eHealthDB`.`EHR` SET `EHRSentStatus`='1' WHERE `EHRID`='" + i + "';");
					}
					
				} else if (unsentResult.getInt("conceptIDFromConcept") == 5 && unsentResult.getInt("EHRSentStatus") == 0) {
					System.out.println("I am sending CAT");
					
					// Getting the EHRIDs for unsent contents
					Statement readUnsentContent = connect.createStatement();
					readUnsentContent
							.executeQuery("SELECT * FROM eHealthDB.EHRContent where EHRIDFromEHR='" + i + "';");
					ResultSet unsentContentResult = readUnsentContent.getResultSet();
					
					Statement readCorrEHRID = connect.createStatement();
					readCorrEHRID
							.executeQuery("SELECT * FROM eHealthDB.EHR WHERE EHRID='" + i + "';");
					ResultSet readCorrEHRIDResult = readCorrEHRID.getResultSet();
					readCorrEHRIDResult.next();					
					
					// New sql statement
					String patientID = readCorrEHRIDResult.getString("pasientID");
					String dateTime = readCorrEHRIDResult.getString("EHRDateTime");
					
					unsentContentResult.next();
					String unsentID26 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV26 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID27 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV27 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID28 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV28 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID29 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV29 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID30 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV30 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID31 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV31 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID32 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV32 = unsentContentResult.getString("parameterValue");
					
					unsentContentResult.next();
					String unsentID33 = unsentContentResult.getString("parameterIDFromConceptParameters");
					String unsentPV33 = unsentContentResult.getString("parameterValue");
			
					readUnsentContent.close();
					unsentContentResult.close();
					readCorrEHRID.close();
					readCorrEHRIDResult.close();
			
					System.out.println(unsentID26 + " " + unsentPV26);
					System.out.println(unsentID27 + " " + unsentPV27);
					System.out.println(unsentID28 + " " + unsentPV28);
					System.out.println(unsentID29 + " " + unsentPV29);
					System.out.println(unsentID30 + " " + unsentPV30);
					System.out.println(unsentID31 + " " + unsentPV31);
					System.out.println(unsentID32 + " " + unsentPV32);
					System.out.println(unsentID33 + " " + unsentPV33);
					
					// HTTP
					String username = HealthProperties.getProperty("iipUsername");
					String password = HealthProperties.getProperty("iipPassword");
					String iipUrl = HealthProperties.getProperty("iipUrl");
					String catChannel = HealthProperties
							.getProperty("catQuestionnaireChannel");
					String URL = "https://" + username + ":" + password + "@" + iipUrl
							+ catChannel;
					Integer statusDaily = 0;

					HttpClient httpclient = getNewHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					HttpResponse response = null;

					// Array to send to IIP

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("q_1", unsentPV26));
					params.add(new BasicNameValuePair("q_2", unsentPV27));
					params.add(new BasicNameValuePair("q_3", unsentPV28));
					params.add(new BasicNameValuePair("q_4", unsentPV29));
					params.add(new BasicNameValuePair("q_5", unsentPV30));
					params.add(new BasicNameValuePair("q_6", unsentPV31));
					params.add(new BasicNameValuePair("q_7", unsentPV32));
					params.add(new BasicNameValuePair("q_8", unsentPV33));
					params.add(new BasicNameValuePair("patientId", patientID));
					params.add(new BasicNameValuePair("dateTime", dateTime));
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						response = httpclient.execute(httpPost);
						System.out.println("\nSending the unsent CAT questionnaire with EHRID " + i +": "
								+ response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {
							statusDaily = 1;
						} else
							statusDaily = 0;

					} catch (ClientProtocolException e1) {
						System.out.println(e1.toString());
					} catch (IOException e1) {
						System.out.println(e1.toString());
					}
					
					if (statusDaily == 1) {
						Statement updateTable = connect.createStatement();
						updateTable
								.executeUpdate("UPDATE `eHealthDB`.`EHR` SET `EHRSentStatus`='1' WHERE `EHRID`='" + i + "';");
					}
				}
				
				// Iterating through EHR table
				unsentResult.next();
			}
			readUnsentFromEHR.close();
			unsentResult.close();
			
		} catch (Exception e) {
			System.out.println("Reading from DB on sending unsent values failed");
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// HTTPS
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