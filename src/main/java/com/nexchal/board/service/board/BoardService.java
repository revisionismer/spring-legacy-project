package com.nexchal.board.service.board;

import java.util.List;

import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.domain.paging.Criteria;

public interface BoardService {

	public Long createBoard(BoardVO boardVO);
	
	public List<BoardVO> readBoardlist();
	
	public List<BoardVO> readBoardlist(Criteria criteria);
	
	public BoardVO readBoardOne(Long bno);
	
	public boolean updateBoard(BoardVO boardVO, Long[] ano);
	
	public boolean deleteBoard(Long bno);
	
	public int getTotalCount(Criteria criteria);
}
