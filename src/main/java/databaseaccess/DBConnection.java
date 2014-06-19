package databaseaccess;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vitalsignals.Pulse;
import app.EHR;
import app.HealthProperties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBConnection {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	private String dbName = HealthProperties.getProperty("dbName");
	private String dbUsername = HealthProperties.getProperty("dbUsername");
	private String dbPassword = HealthProperties.getProperty("dbPassword");
	private int patientId = Integer.parseInt(HealthProperties
			.getProperty("patientId"));

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
			connect.setAutoCommit(false);
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
							+ ".ehrcontent (EHRIDFromEHR, parameterIDFromConceptParameters, parameterValue) values (?,?,?)");
			// Insert Pulse
			preparedStatement.setInt(1, pulseEHRID);
			preparedStatement.setInt(2, 1);
			preparedStatement.setString(3, pulse.getPulse());
			preparedStatement.addBatch();
			// Insert Oxygen(SPO2)
			preparedStatement.setInt(1, oxygenEHRID);
			preparedStatement.setInt(2, 4);
			preparedStatement.setString(3, pulse.getOxigen());
			preparedStatement.addBatch();
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		String dateAndTime = simpleDateFormat.format(pulse.getDate());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			connect.setAutoCommit(false);
			System.out.println(connect.toString());
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into "
							+ dbName
							+ ".ehr (pasientID, conceptIDFromConcept, EHRDateTime, EHRSentStatus) values (?,?,?,?)");
			// For pulse
			preparedStatement.setInt(1, patientId);
			preparedStatement.setInt(2, 1);
			preparedStatement.setString(3, dateAndTime);
			preparedStatement.setInt(4, isSend);
			preparedStatement.addBatch();
			// For oxygen
			preparedStatement.setInt(1, patientId);
			preparedStatement.setInt(2, 2);
			preparedStatement.setString(3, dateAndTime);
			preparedStatement.setInt(4, isSend);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			System.out.println("Starting commit");
			connect.commit();
		} catch (SQLException e) {
			System.out.println("Couldn't save EHR to database" + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find class com.mysql.jdbc.Driver" + e);
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Got null pointer Exception" + e);
			e.printStackTrace();
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
				return new Pulse(pulseValue, oxygenValue, EHRDateTime);
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

	public List<Integer> getDaysWithMeasurements(int month) {
		List<Integer> daysWithMeasurements = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("select dayofmonth(e.EHRDateTime) from ehr as e where MONTH(e.EHRDateTime)=? and e.conceptIDFromConcept=1");
			preparedStatement.setInt(1, month + 1);
			ResultSet rs = preparedStatement.getResultSet();
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int day = rs.getInt(1);
				if (!daysWithMeasurements.contains(day)) {
					daysWithMeasurements.add(day);
				}
			}
		} catch (SQLException e) {
			System.out
					.println("Exception in gettion Pulse from EHR to database"
							+ e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return daysWithMeasurements;
	}

	public List<Pulse> getAllMeasurements() {
		List<Pulse> measurements = new ArrayList<>();
		for (int month = 0; month <= 11; month++) {
			List<Integer> days = getDaysWithMeasurements(month);
			for (Integer d : days) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(2014, month, d);
				Date date = calendar.getTime();
				Pulse pulse = getMeasurementsForDay(date);
				if (pulse != null) {
					measurements.add(pulse);
				}
			}
		}
		return measurements;
	}
}
