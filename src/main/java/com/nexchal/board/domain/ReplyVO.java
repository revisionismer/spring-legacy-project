package com.nexchal.board.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {

	private Long rno;
	
	private Long bno;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private boolean deleteYn;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
}

/*
 * CREATE TABLE tb_reply(
	rno INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bno INT(11) NOT NULL,
	content VARCHAR(2000) NOT NULL COLLATE 'utf8_general_ci',
	writer VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',
	createDate TIMESTAMP NOT NULL DEFAULT current_timestamp(),
	updateDate TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	deleteYn TINYINT(4) NULL DEFAULT NULL,
	CONSTRAINT fk_reply_board FOREIGN KEY(bno) REFERENCES tb_board(bno)
) 
 * 
 */
