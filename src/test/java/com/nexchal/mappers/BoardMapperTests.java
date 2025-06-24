package com.nexchal.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexchal.board.mappers.board.BoardMapper;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

	@Autowired(required = false)
	BoardMapper boardMapper;
	
	@Test
	public void test01() {
		log.info(boardMapper);
	}
	
	@Test
	public void test02() {
		boardMapper.getAllList().forEach(boardVO -> log.info(boardVO));
	}
}
