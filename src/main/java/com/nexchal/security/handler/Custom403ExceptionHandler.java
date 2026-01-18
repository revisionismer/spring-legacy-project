package com.nexchal.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Custom403ExceptionHandler implements AccessDeniedHandler {

	@Override
	public void handle(
			HttpServletRequest request, 
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.info("-----------------------------------");
		
		log.info("---------- Access Denied ----------");
		
		response.sendRedirect("/user/accessDenied");
		
	}

}
