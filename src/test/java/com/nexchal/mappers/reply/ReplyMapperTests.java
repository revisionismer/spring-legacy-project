package com.nexchal.mappers.reply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexchal.board.domain.ReplyVO;
import com.nexchal.board.domain.paging.Criteria;
import com.nexchal.board.mappers.reply.ReplyMapper;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {

	@Autowired(required = false)
	ReplyMapper replyMapper;
	
	@Test
	public void testInsertReply() {
		Long bno = 36L;
		
		for(int i = 0; i < 10; i++) {
			ReplyVO replyVO = ReplyVO.builder()
					.bno(bno)
					.title("Reply Test Title" + (i + 1))
					.content("Reply Test Content" + (i + 1))
					.writer("Tester")
					.build();
					
			log.info(replyMapper.insertReply(replyVO));
		}
	}
	
	@Test
	public void testFindOneReply() {
		
		log.info(replyMapper.findReplyOne(5L));
	}
	
	@Test
	public void testReplyDelete() {
		ReplyVO replyVO = replyMapper.findReplyOne(5L);
		replyVO.setDeleteYn(true);
		
		log.info(replyMapper.updateReply(replyVO));
	}
	
	@Test
	public void testReplyUpdate() {
		ReplyVO replyVO = replyMapper.findReplyOne(5L);
		replyVO.setTitle("Reply Test Title5(수정)");
		replyVO.setContent("Reply Test Content5(수정)");
		
		replyVO.setDeleteYn(false);
		
		log.info(replyMapper.updateReply(replyVO));
	}
	
	@Test
	public void testReplyListSelect() {
		
		// 1, 10
		Criteria criteria = new Criteria();
		
	    replyMapper.findAllReplyList(criteria, 36L).forEach(replyVO -> log.info(replyVO));
	}
	
	@Test
	public void testReplyTotalCount() {
		
		Long bno = 36L;
		
		log.info(replyMapper.getTotalCount(null, bno));
	}
}
