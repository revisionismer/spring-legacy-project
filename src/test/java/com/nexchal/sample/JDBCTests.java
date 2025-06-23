package com.nexchal.sample;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCTests {

	@Test
	public void testConnection() throws Exception {
		// DB Driver Class
		Class.forName("org.mariadb.jdbc.Driver");
		
		// URL
		Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/nexchal", "root", "1234");
		System.out.println(connection);
		
		// close
		connection.close();
	}
}
