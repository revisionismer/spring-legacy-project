package com.nexchal.board.service.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexchal.board.domain.AttachFileVO;
import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.domain.paging.Criteria;
import com.nexchal.board.mappers.board.BoardFileMapper;
import com.nexchal.board.mappers.board.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final BoardFileMapper boardFileMapper;

	@Override
	public Long createBoard(BoardVO boardVO) {
		log.info("-----------------------" + boardVO);
		
		int count = boardMapper.insertBoard(boardVO);
		
		log.info("-----------------------" + count);
		
		Long bno = boardVO.getBno();
		
		List<AttachFileVO> attachFileList = boardVO.getAttachFileList();
		
		if(attachFileList != null && attachFileList.size() > 0) {
			
			for(AttachFileVO attachFileVO : attachFileList) {
				
				attachFileVO.setBno(bno);
				
				boardFileMapper.insertAttachFile(attachFileVO);
			}
		}
		
		return bno;
	}

	@Override
	public List<BoardVO> readBoardlist() {
		return boardMapper.getAllList();
	}
	
	@Override
	public List<BoardVO> readBoardlist(Criteria criteria) {
		return boardMapper.getAllListByPaging(criteria);
	}

	@Override
	public BoardVO readBoardOne(Long bno) {
		return boardMapper.selectBoard(bno);
	}

	@Override
	public boolean updateBoard(BoardVO boardVO) {
		
		int count = boardMapper.updateBoard(boardVO);
		
		List<AttachFileVO> attachFileList = boardVO.getAttachFileList();
		
		if(attachFileList != null && attachFileList.size() > 0 && count == 1) {
			
			for(AttachFileVO attachFileVO : attachFileList) {
				
				attachFileVO.setBno(boardVO.getBno());
				
				boardFileMapper.insertAttachFile(attachFileVO);
			}
		}
		
		return count == 1 ? true : false;
	}

	@Override
	public boolean deleteBoard(Long bno) {
		return true;
	}

	@Override
	public int getTotalCount(Criteria criteria) {
		return boardMapper.getTotalCount(criteria);
	}
	
}
