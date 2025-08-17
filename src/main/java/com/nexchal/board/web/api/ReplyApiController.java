package com.nexchal.board.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replies")
public class ReplyApiController {

	@GetMapping("/sample01")
	public Map<String, String> sample01() {
		Map<String, String> result = new HashMap<>();
		
		result.put("v1", "AAA");
		result.put("v2", "BBB");
		
		return result;
	}
	
	@GetMapping("/sample02")
	public String sample02() {
		return "Hello World";
	}
	
	// consumes : 해당 맵핑이 어떤 데이터를 소비하는지 명시, produces : return시 어떤 데이터로 반환할 것인지
	@GetMapping(value = "/sample03", consumes= {}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sample03() {
		return "Hello World";
	}
}
