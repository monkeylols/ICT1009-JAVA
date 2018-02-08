
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String connectionUrl = "jdbc:mysql://localhost:3306/ict1009java";
	private static final String usrname = "root";
	private static final String password = "";

	public Connection getConnection() throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		// Declare the JDBC objects.
		Connection con = null;
		try {
			con = DriverManager.getConnection(connectionUrl, usrname, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con = null;
			e.printStackTrace();
		}
		return con;

	}
	
	
}
