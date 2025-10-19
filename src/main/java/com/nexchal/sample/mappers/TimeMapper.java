package com.nexchal.sample.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

	@Select("select now()")
	String getTime();
	
	String getTime2();
	
	@Insert("insert into tb_sample1 (col) values (#{str})")
	int insert1(String str);
	
	@Insert("insert into tb_sample2 (col) values (#{str})")
	int insert2(String str);
}
