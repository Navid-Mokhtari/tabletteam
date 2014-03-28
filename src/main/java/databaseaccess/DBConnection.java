package databaseaccess;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vitalsignals.Pulse;
import app.EHR;
import app.HealthProperties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
//import com.sun.tools.javac.resources.javac;

public class DBConnection {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	private String dbName = HealthProperties.getProperty("dbName");
	private String dbUsername = HealthProperties.getProperty("dbUsername");
	private String dbPassword = HealthProperties.getProperty("dbPassword");
	private String patientID = HealthProperties.getProperty("patientId");

	public void savePulseAndOxygen(Pulse pulse, boolean isSent) {
		saveEHR(pulse, isSent);
		savePulse(pulse);

	}

	private void savePulse(Pulse pulse) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			Statement s = (Statement) connect.createStatement();
			s.executeQuery("SELECT `EHRID` FROM `EHR` WHERE `conceptIDFromConcept` = 1 ORDER BY EHRID DESC LIMIT 1");
			ResultSet rs = s.getResultSet();
			rs.next();
			int pulseEHRID = rs.getInt("EHRID");
			System.out.println("Latest pulse EHRID read from DB = "
					+ pulseEHRID);
			s.executeQuery("SELECT `EHRID` FROM `EHR` WHERE `conceptIDFromConcept` = 2 ORDER BY EHRID DESC LIMIT 1");
			rs = s.getResultSet();
			rs.next();
			int oxygenEHRID = rs.getInt("EHRID");
			System.out.println("Latest oxygen EHRID read from DB = "
					+ oxygenEHRID);
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into "
							+ dbName
							+ ".ehrcontent (EHRContentID,EHRIDFromEHR, parameterIDFromConceptParameters, parameterValue) values (?,?,?,?)");
			// Insert Pulse
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, pulseEHRID);
			preparedStatement.setInt(3, 1);
			preparedStatement.setString(4, pulse.getPulse());
			preparedStatement.addBatch();
			// Insert Oxygen(SPO2)
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, oxygenEHRID);
			preparedStatement.setInt(3, 5);
			preparedStatement.setString(4, pulse.getOxigen());
			preparedStatement.executeBatch();
			preparedStatement = null;
			connect.commit();
		} catch (Exception e) {
			System.out
					.println("Couldn't save pulse to database" + e.toString());
		} finally {
			close();
		}
	}

	private void saveEHR(Pulse pulse, boolean isSent) {
		Integer isSend = isSent ? 1 : 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into "
							+ dbName
							+ ".ehr (EHRID, pasientID, conceptIDFromConcept, EHRDateTime, EHRSentStatus) values (?,?,?,?,?)");
			// For pulse
			preparedStatement.setNull(1, Types.INTEGER);
			preparedStatement.setInt(2, Integer.getInteger(patientID));
			preparedStatement.setInt(3, 1);
			preparedStatement.setString(4, pulse.getDate().toString());
			preparedStatement.setInt(5, isSend);
			preparedStatement.addBatch();
			// For oxygen
			preparedStatement.setNull(1, Types.INTEGER);
			preparedStatement.setInt(2, Integer.getInteger(patientID));
			preparedStatement.setInt(3, 2);
			preparedStatement.setString(4, pulse.getDate().toString());
			preparedStatement.setInt(5, isSend);
			preparedStatement.executeBatch();
			preparedStatement = null;
			connect.commit();
		} catch (Exception e) {
			System.out.println("Couldn't save EHR to database" + e.toString());
		} finally {
			close();
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public Pulse getMeasurementsForDay(Date date) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			preparedStatement = (PreparedStatement) connect
					.prepareStatement(" select * from (Select * from ehr order by ehrDateTime desc) as t where DATE(EHRDateTime) = ? and (conceptIDFromConcept=1 or conceptIDFromConcept=2) group by conceptIDFromConcept desc");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.executeQuery();
			List<EHR> ehrs = new ArrayList<EHR>();
			ResultSet rs = preparedStatement.getResultSet();
			Date EHRDateTime = null;
			while (rs.next()) {
				long EHRID = Long.valueOf(rs.getString(1));
				long patientID = Long.valueOf(rs.getString(2));
				int conceptIDFromConcept = Integer.valueOf(rs.getString(3));
				EHRDateTime = rs.getTimestamp(4);
				boolean EHRSentStatus = rs.getBoolean(5);
				EHR ehr = new EHR(EHRID, patientID, conceptIDFromConcept,
						EHRDateTime, EHRSentStatus);
				ehrs.add(ehr);
			}
			resultSet = null;
			String pulseValue = null;
			String oxygenValue = null;
			// Parameter value for pulse is 1, for oxygen(spo2) is 4.
			// Concepts ID : 1,2 - respectively
			if (!ehrs.isEmpty()) {

				for (EHR ehr : ehrs) {
					if (ehr.getConceptIDFromConcept() == 1) {
						preparedStatement = (PreparedStatement) connect
								.prepareStatement("Select parameterValue from ehrcontent where EHRIDFromEHR=? and parameterIDFromConceptParameters = 1");
						preparedStatement.setString(1,
								String.valueOf(ehr.getEHRID()));
						rs = preparedStatement.executeQuery();
						rs.next();
						pulseValue = rs.getString(1);
						rs = null;
					}
					if (ehr.getConceptIDFromConcept() == 2) {
						preparedStatement = (PreparedStatement) connect
								.prepareStatement("Select parameterValue from ehrcontent where EHRIDFromEHR=? and parameterIDFromConceptParameters = 4");
						preparedStatement.setString(1,
								String.valueOf(ehr.getEHRID()));
						rs = preparedStatement.executeQuery();
						rs.next();
						oxygenValue = rs.getString(1);
						rs = null;
					}
				}
				return new Pulse(pulseValue, oxygenValue,
						EHRDateTime);
			}
			return null;
		} catch (SQLException e) {
			System.out
					.println("Exception in gettion Pulse from EHR to database"
							+ e.toString());
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
}
