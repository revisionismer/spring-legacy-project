package com.nexchal.board.service.reply;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexchal.board.domain.ReplyVO;
import com.nexchal.board.domain.paging.Criteria;
import com.nexchal.board.mappers.reply.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyMapper replyMapper;
	
	@Override
	public Long registerReply(ReplyVO replyVO) {
		log.info("-----------registerReply()-----------");
		return replyMapper.insertReply(replyVO);
	}

	@Override
	public ReplyVO getReply(Long rno) {
		log.info("-----------getReply()-----------");
		return replyMapper.findReplyOne(rno);
	}

	@Override
	public int modifyReply(ReplyVO replyVO) {
		log.info("-----------modifyReply()-----------");
		return replyMapper.updateReply(replyVO);
	}

	@Override
	public List<ReplyVO> getRelpyListWithBno(Criteria criteria, Long bno) {
		log.info("-----------getRelpyListWithBno()-----------");
		return replyMapper.findAllReplyList(criteria, bno);
	}

	@Override
	public int getTotalReplyWithBno(Criteria criteria, Long bno) {
		log.info("-----------getTotalReplyWithBno()-----------");
		return replyMapper.getTotalCount(criteria, bno);
	}

}
