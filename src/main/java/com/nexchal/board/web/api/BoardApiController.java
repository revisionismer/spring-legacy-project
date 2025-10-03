package com.nexchal.board.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardApiController {

	@GetMapping("")
	public Map<String, Object> test() {
		
		Map<String, Object> result = new HashMap<>();
		
		log.info("test");
	
		return result;
		
	}
}
