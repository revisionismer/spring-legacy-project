package com.nexchal.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"
})
@Log4j2
public class BCryptTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testPasswordEncoder() {
		String str = "1111";
		
		String encStr = passwordEncoder.encode(str);
		
		log.info(encStr);
		
		// 1-1. 1111이라는 문자가 encStr이라는 인코딩된 문자로 변환되는게 가능해? (매번 인코딩 문자가 달라진다)
		log.info(passwordEncoder.matches(str, encStr));
		
	}
}
