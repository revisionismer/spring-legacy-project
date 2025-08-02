package com.nexchal.board.domain.paging;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum = 1;
	private int amount = 10;
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		
		if(pageNum <= 0) {
			this.pageNum = 1;
			return;
		}
		
		this.pageNum = pageNum;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		
		if(amount <= 10 || amount > 100) {
			this.amount = 10;
			return;
		}
		
		this.amount = amount;
	}
	
	public int getSkip() {
		// 1 페이지 : 1 - 1 * 10, 10 => skip할 데이터 0개, 출력할 데이터 10개
		// 2 페이지 : 2 - 1 * 10, 10 => skip할 데이터 10개, 출력할 데이터 10개
		// 3 페이지 : 3 - 1 * 10, 10 => skip할 데이터 20개, 출력할 데이터 10개
		// 4 페이지 : 4 - 1 * 10, 10 => skip할 데이터 30개, 출력할 데이터 10개
		
		return (this.pageNum - 1) * this.amount;
	}
	

}
