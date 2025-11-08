package com.nexchal.board.mappers.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.domain.paging.Criteria;

@Mapper
public interface BoardMapper {

	List<BoardVO> getAllList();
	
	List<BoardVO> getAllListByPaging(Criteria criteria);
	
	int getTotalCount(Criteria criteria);
	
	int insertBoard(BoardVO boardVO);
	
	BoardVO selectBoard(Long bno);
	
	int updateBoard(BoardVO boardVO);
}
