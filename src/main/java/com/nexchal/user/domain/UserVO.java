package com.nexchal.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	
	private Long uno;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private List<UserAuthVO> roles;
	
	private boolean deleteYn;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime createDate;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime updateDate;
}

/*
 * -user table 생성 -
CREATE TABLE tb_user(
	`uno` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`username` VARCHAR(100) NOT NULL UNIQUE KEY,
	`password` VARCHAR(100) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`createDate` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
	`updateDate` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	`deleteYn` TINYINT(4) NULL DEFAULT NULL
); 

 */
