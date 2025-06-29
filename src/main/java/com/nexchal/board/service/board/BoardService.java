package com.nexchal.board.service.board;

import java.util.List;

import com.nexchal.board.domain.BoardVO;

public interface BoardService {

	public Long createBoard(BoardVO boardVO);
	
	public List<BoardVO> readBoardlist();
	
	public BoardVO readBoardOne(Long bno);
	
	public boolean updateBoard(BoardVO boardVO);
	
	public boolean deleteBoard(Long bno);
}
