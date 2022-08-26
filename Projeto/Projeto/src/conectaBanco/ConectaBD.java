package conectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {
	public static Connection createConnectionToMySQL() throws SQLException {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda","root","");
		return conn;
				
	}catch (ClassNotFoundException e){
		throw new SQLException(e.getException());
	}
		
	}
	
public static void main(String[] args) throws SQLException, ClassNotFoundException {
	Connection conn = createConnectionToMySQL();
	if(conn!=null) {
		System.out.println("conectado!!!");
		conn.close();
	}
}
}