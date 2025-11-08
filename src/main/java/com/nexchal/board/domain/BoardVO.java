package com.nexchal.board.domain;

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
public class BoardVO {
	
	private Long bno;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private boolean deleteYn;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime createDate;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime updateDate;
	
	private List<AttachFileVO> attachFileList;
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
 