package com.nexchal.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexchal.sample.mappers.TimeMapper;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TimeTests {

	@Autowired(required = false)
	TimeMapper timeMapper;
	
	@Test
	public void test1() {
		
		log.info(timeMapper.getClass().getName());
		
		log.info("----------------------------");
		
		log.info(timeMapper.getTime());
		
		log.info("----------------------------");
		
		log.info("----------------------------");
	}
	
	@Test
	public void test2() {
		log.info(timeMapper.getTime2());
	}
}
