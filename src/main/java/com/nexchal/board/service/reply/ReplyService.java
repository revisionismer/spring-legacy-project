package com.nexchal.board.service.reply;

import java.util.List;

import com.nexchal.board.domain.ReplyVO;
import com.nexchal.board.domain.paging.Criteria;

public interface ReplyService {

	Long registerReply(ReplyVO replyVO);
	
	ReplyVO getReply(Long rno);
	
	int modifyReply(ReplyVO replyVO);
	
	List<ReplyVO> getRelpyListWithBno(Criteria criteria, Long bno);
	
	int getTotalReplyWithBno(Criteria criteria, Long bno);
}
