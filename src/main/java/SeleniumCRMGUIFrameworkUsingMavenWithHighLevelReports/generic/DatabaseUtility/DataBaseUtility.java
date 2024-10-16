package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection con;

	public void getDbconnection(String url, String username, String password) throws SQLException {
		// Step 1 : Load/Register the database driver
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			// Step 2 : Connect To The Database
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
		}
	}
	
	public void getDbconnection() throws SQLException {
		// Step 1 : Load/Register the database driver
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			// Step 2 : Connect To The Database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");;
		} catch (Exception e) {
		}
	}
	public void closeDbConnection() throws SQLException {
		// Step 1 : Close The Connection
		con.close();
	}

	public ResultSet executeConSelectQuery(String query) {
		ResultSet result = null;
		;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);
		} catch (Exception e) {
		}
		return result;
	}
	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return result;
	}
}
