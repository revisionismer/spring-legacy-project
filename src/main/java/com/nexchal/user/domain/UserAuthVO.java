package com.nexchal.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthVO {
	
	private Long uano;
	private String username;
	private String role;
}

/*
 *- userauth table 생성 -
CREATE TABLE tb_userAuth(
	uano INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(100) NOT NULL,
	role VARCHAR(50) NOT NULL
);

ALTER TABLE tb_userauth add constraint fk_auth FOREIGN KEY(username) REFERENCES tb_user(username);

 * 
 */
