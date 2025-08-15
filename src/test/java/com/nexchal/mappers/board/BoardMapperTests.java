package com.nexchal.mappers.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.domain.paging.Criteria;
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
	
	@Test
	public void test03() {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("New Test Title");
		boardVO.setContent("New Test Content");
		boardVO.setWriter("New Test Writer");
		
		log.info("COUNT : " + boardMapper.insert(boardVO));
		
		log.info("BNO : " + boardVO.getBno());
	}
	
	
	@Test
	public void test04() {
		
		Long bno = 3L;
		
		log.info(boardMapper.select(bno));
	}
	
	@Test
	public void test05() {
		
		Long bno = 2L;
		
		BoardVO findBoard = boardMapper.select(bno);
		findBoard.setTitle("New Test Title(수정)");
		findBoard.setContent("New Test Content(수정)");
		
		int updateCount = boardMapper.update(findBoard);
		
		log.info("update count : " + updateCount);
	}
	
	@Test
	public void test06() {
		Criteria criteria = new Criteria();
		// 1, 10
		
		List<BoardVO> list = boardMapper.getAllListByPaging(criteria);
		
		list.forEach(boardVO -> log.info(boardVO));
		
	}
	
	@Test
	public void test07() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(2);
		criteria.setTypes(new String[] {"T", "C", "W"});
		criteria.setKeyword("New");
		
		List<BoardVO> list = boardMapper.getAllListByPaging(criteria);
		
		list.forEach(boardVO -> log.info(boardVO));
	}
}
