package com.nexchal.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nexchal.user.domain.UserVO;
import com.nexchal.user.mappers.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	/* 1-1. 롬복 주입 방식으로 하면 내 프로젝트에선 에러가 나기 떄문에 security-context.xml에 기본 생성자 설정이 추가로 들어가 줘야 한다. */
	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO userVO = userMapper.selectUser(username);
		
		log.info(userVO);
		
		if(userVO == null) {
			throw new UsernameNotFoundException("User Not Founnd");
		}
		
		return new CustomUserDetails(userVO);
	}

}
