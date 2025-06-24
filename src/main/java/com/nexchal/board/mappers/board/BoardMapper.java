package com.nexchal.board.mappers.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nexchal.board.domain.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> getAllList();
}
