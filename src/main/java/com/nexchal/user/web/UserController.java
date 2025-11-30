package com.nexchal.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginPage(String error, String logout, Model model) {
		
		log.info("---- Custom LoginPage ----");
		
		if(error != null) {
			log.info("---- error -----");
			log.info(error);
			log.info("----------------");
		}
	}
}
