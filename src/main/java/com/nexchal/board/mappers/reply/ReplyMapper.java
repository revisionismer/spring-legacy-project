package com.nexchal.board.mappers.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nexchal.board.domain.ReplyVO;
import com.nexchal.board.domain.paging.Criteria;

@Mapper
public interface ReplyMapper {

	Long insertReply(ReplyVO replyVO);
	
	ReplyVO findReplyOne(Long rno);
	
	int updateReply(ReplyVO replyVO);
	
	List<ReplyVO> findAllReplyList(@Param("cri") Criteria criteria, @Param("bno") Long bno);

	int getTotalCount(@Param("cri") Criteria criteria, @Param("bno") Long bno);
}

