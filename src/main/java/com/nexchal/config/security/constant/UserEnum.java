package com.nexchal.config.security.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserEnum {
	ROLE_ADMIN("관리자"), ROLE_USER("일반 사용자");
	
	private String value;
}
