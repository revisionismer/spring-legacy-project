package com.nexchal.sample.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexchal.sample.mappers.TimeMapper;
import com.nexchal.sample.service.TimeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class SampleApiController {

	private final TimeService timeService;
	private final TimeMapper timeMapper;
	
	@GetMapping("/sample") 
	public Map<String, Object> sample(String str) throws Exception {
		log.info("-----------transactionTest Controller ---------------");
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("res", timeMapper.getTime());
	
		return result;
		
	}
	
	@GetMapping("/test") 
	public Map<String, Object> transactionTest3(@RequestParam("str") String str) {
		log.info("-----------transactionTest Controller ---------------");
		
		Map<String, Object> result = new HashMap<>();
		
		timeService.insertBoth(str);
		
		result.put("res", "success");
		
		return result;
		
	}
	

}
