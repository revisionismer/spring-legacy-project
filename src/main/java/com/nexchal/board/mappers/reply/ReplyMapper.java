package com.nexchal.board.mappers.reply;

import com.nexchal.board.domain.ReplyVO;

public interface ReplyMapper {

	Long insertReply(ReplyVO replyVO);
	
	ReplyVO findReplyOne(Long rno);
	
	int updateReply(ReplyVO replyVO);
}

