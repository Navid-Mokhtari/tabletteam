package databaseaccess;

import java.sql.DriverManager;
import java.sql.ResultSet;

import vitalsignals.Pulse;
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

	public void savePulse(Pulse pulse) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into "
							+ dbName
							+ ".ehrcontent (EHRContentID,EHRIDFromEHR, parameterIDFromConceptParameters, parameterValue) values (?,?,?,?)");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1);
			preparedStatement.setInt(3, 1);
			preparedStatement.setString(4, "test");
			preparedStatement.executeUpdate();
			connect.setAutoCommit(false);
			preparedStatement = null;
			connect.commit();
		} catch (Exception e) {
			throw e;
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
}
