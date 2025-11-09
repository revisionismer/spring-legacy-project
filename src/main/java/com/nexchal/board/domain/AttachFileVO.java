package com.nexchal.board.domain;

import lombok.Data;

@Data
public class AttachFileVO {
	
	private Long ano;
	private Long bno;
	
	private String uuid;
	
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
 