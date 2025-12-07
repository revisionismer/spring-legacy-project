package com.nexchal.user.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.nexchal.user.domain.UserAuthVO;
import com.nexchal.user.domain.UserVO;

@Mapper
public interface UserMapper {

	int joinUser(UserVO userVO);
	
	int insertUserAuth(UserAuthVO authVO);

}
