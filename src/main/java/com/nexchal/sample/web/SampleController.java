package com.nexchal.sample.web;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		return "/sample/basic";
	}
	
	@GetMapping("/ex01")
	public String ex1(@RequestParam("name") String name,
					  @RequestParam(
							  value="age",
							  required = false,
							  defaultValue = "10") int age) {  // defaultValue가 숫자가 아니면 익셉션동작.
		
		log.info("ex01---------------------------");
		log.info(name);
		log.info(age);
		
		return "/sample/ex01";
		
	}
	
	@GetMapping("/ex02")
	public void ex2(SampleDTO dto) {
		log.info("ex2---------------------------");
		log.info(dto);
	}
	
	@GetMapping("/ex03")
	public String ex03(String[] ids) {
		log.info("ex03---------------------------");
		log.info(Arrays.deepToString(ids));
		return "/sample/ex3";
	}
	
	// Tip : Array를 파라미터로 넘길 때 : http://localhost:8080/sample/ex04?list%5B0%5D.name=A&list%5B0%5D.age=10&list%5B1%5D.name=B&list%5B1%5D.age=20
	@GetMapping("/ex04")
	public void ex04(SampleDTOList list) {
		log.info("ex04---------------------------");
		log.info(list);
	}
	
	@GetMapping("/ex05")
	public void ex05(SampleTodoDTO dto) {
		log.info("ex05---------------------------");
		log.info(dto);
	}
	
	@GetMapping("/ex06")
	public String ex06(@ModelAttribute("dto") SampleDTO dto,
					 @ModelAttribute("page") int page, 
					 Model model) {
		
		model.addAttribute("list", new String[] {"AAA", "BBB", "CCC"});
		
		return "/sample/ex04";
		
	}
	
	@GetMapping("/ex07")
	public String ex07(RedirectAttributes rttr) {
		
		rttr.addAttribute("v1", "ABC");
		rttr.addAttribute("v2", "DFG");
		
		rttr.addFlashAttribute("core", "ABCDE");
		
		return "redirect:/sample/basic";
	}
	
	@GetMapping("/all")
	public String ex08_모든사용자() {
		log.info("-------- 스프링 시큐리티 테스트(비회원) --------------");
		
		return "/sample/all";
	}
	
	@GetMapping("/member")
	public String ex08_회원인사용자() {
		log.info("-------- 스프링 시큐리티 테스트(회원) --------------");
		
		return "/sample/member";
	}
	
	@GetMapping("/admin")
	public String ex08_관리자인사용자() {
		log.info("-------- 스프링 시큐리티 테스트(관리자) --------------");
		
		return "/sample/admin";
	}
}
