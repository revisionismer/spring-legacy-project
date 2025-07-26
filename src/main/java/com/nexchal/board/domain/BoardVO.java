package com.nexchal.board.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long bno;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private boolean deleteYn;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
}

/*
 
	create table tb_board (
		bno int auto_increment primary key,
		title varchar(500) not null,
		content varchar(2000) not null,
		writer varchar(100) not null,
		createDate timestamp default now(),
		updateDate timestamp
	);
	
	insert into tb_board(title, content, writer)
	values('Test title', 'Test content', 'Test writer');
	
	select * from tb_board;
 
 */
 