package com.nexchal.board.domain;

import lombok.Data;

@Data
public class AttachFileVO {
	// 1-1. 첨부 파일 번호
	private Long ano;
	
	// 1-2. 첨부 파일이 들어가 있는 게시글 번호.
	private Long bno;
	
	// 1-3. uuid
	private String uuid;
	
	// 1-4. filename
	private String filename;
	
	public String getSavedFileName() {
		
		if(ano == null) {
			return null;
		}
		
		return uuid + "_" + filename;
	}
}

/*
 * 
	CREATE TABLE tb_attach(
		ano INT AUTO_INCREMENT PRIMARY KEY,
		UUID VARCHAR(50) NULL,
		bno INT NOT NULL,
		filename VARCHAR(500) NOT NULL
   	);	

	CREATE INDEX idx_attach ON tb_attach(bno DESC, ano ASC);
 * 
 */
 