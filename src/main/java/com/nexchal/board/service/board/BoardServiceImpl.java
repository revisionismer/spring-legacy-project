package com.nexchal.board.service.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.mappers.board.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	public Long createBoard(BoardVO boardVO) {
		log.info("-----------------------" + boardVO);
		
		int count = boardMapper.insert(boardVO);
		
		log.info("-----------------------" + count);
		
		Long bno = boardVO.getBno();
		
		return bno;
	}

	@Override
	public List<BoardVO> readBoardlist() {
		return boardMapper.getAllList();
	}

	@Override
	public BoardVO readBoardOne(Long bno) {
		return boardMapper.select(bno);
	}

	@Override
	public boolean updateBoard(BoardVO boardVO) {
		return boardMapper.update(boardVO) == 1 ? true : false;
	}

	@Override
	public boolean deleteBoard(Long bno) {
		return true;
	}
	
}
