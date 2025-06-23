package com.nexchal.sample.web;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexchal.sample.web.dto.SampleDTO;
import com.nexchal.sample.web.dto.SampleDTOList;
import com.nexchal.sample.web.dto.SampleTodoDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

	@GetMapping("/basic")
	public String basic() {
		log.info("basic------------------------");
		
		return "basic";
	}
	
	@GetMapping("/ex1")
	public void ex1(@RequestParam("name") String name,
					@RequestParam(value="age",
								  required = false,
								  defaultValue = "10") int age) {
		
		log.info("ex1---------------------------");
		log.info(name);
		log.info(age);
		
	}
	
	@GetMapping("/ex2")
	public void ex2(SampleDTO dto) {
		log.info("ex2---------------------------");
		log.info(dto);
	}
	
	@GetMapping("/ex3")
	public void ex03(String[] ids) {
		log.info("ex3---------------------------");
		log.info(Arrays.deepToString(ids));
	}
	
	// Tip : Array를 파라미터로 넘길 때 : http://localhost:8080/sample/ex04?list%5B0%5D.name=A&list%5B0%5D.age=10&list%5B1%5D.name=B&list%5B1%5D.age=20
	@GetMapping("/ex04")
	public void ex04(SampleDTOList list) {
		log.info("ex4---------------------------");
		log.info(list);
	}
	
	@GetMapping("/ex05")
	public void ex05(SampleTodoDTO dto) {
		log.info("ex5---------------------------");
		log.info(dto);
	}
}
