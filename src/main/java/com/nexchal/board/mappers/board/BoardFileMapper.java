package com.nexchal.board.mappers.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nexchal.board.domain.AttachFileVO;

@Mapper
public interface BoardFileMapper {

	int insertAttachFile(AttachFileVO attachFileVO);
	
	void deleteAttachFiles(@Param("anos") Long[] anos);
}
