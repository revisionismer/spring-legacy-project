package com.nexchal.sample;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DITests {

	@Autowired
	Restaurant restaurant;
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void testExist() {
		System.out.println(restaurant);
	}
	
	@Test
	public void testConnection() throws Exception {
		
		Connection con = dataSource.getConnection();
		
		System.out.println(con);
		
		con.close();
	}
}
