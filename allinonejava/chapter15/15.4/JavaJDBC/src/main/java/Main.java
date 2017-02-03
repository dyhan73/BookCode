import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/order_system";
		String username = "root";
		String password = "1234";
		String sql = "select * from customer";
		Connection conn = null;
		// PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			System.setProperty("jdbc.drivers",  "com.mysql.jdbc.Driver");
//			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			// stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			/*
			while (rs.next()) {
				System.out.println(rs.getInt("customer_id") + " : " + rs.getString("name") + ", " + rs.getString(3) + ", " + rs.getString(4));
			}
			*/
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet crs = factory.createCachedRowSet();
			crs.populate(rs);
			rs.close();
			stmt.close();
			connection.close();
	
			while(crs.next()) {
				System.out.println(crs.getInt("customer_id") + " : " + crs.getString("name") + ", " + crs.getString(3) + ", " + crs.getString(4));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
