package com.nexchal.board.domain.paging;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pagination {

	private int startPage;  // 1-1. 시작 페이지 번호
	private int endPage;  // 1-2. 화면에서 마지막 페이지 번호
	
	private boolean prev;  // 1-3. 이전 페이지 여부
	private boolean next;  // 1-4. 다음 페이지 여부
	
	private Criteria criteria;  // 1-5. criteria
	private int totalCount;  // 1-6. 전체  게시글 갯수
	
	public Pagination(Criteria criteria, int totalCount) {	
		this.criteria = criteria;
		this.totalCount = totalCount;
		
		// 1-7. 여기서 10.0은 한 페이지 블록에 뿌려 줄 페이지 번호 갯수
		this.endPage = (int) (Math.ceil(criteria.getPageNum() / 10.0)) * 10;
		
		// 1-8. 시작 페이지 계산
		this.startPage = this.endPage - (10 - 1);
		
		// 1-9. 
		int realEndPage = (int) (Math.ceil(totalCount * 1.0) / criteria.getAmount() + 1);  // 2025-09-27 : +1해주닌까 정상 동작.
	
		// 1-10.
		if(realEndPage <= this.endPage) {
			this.endPage = realEndPage;
		}
		
		// 1-11.
		this.prev = this.startPage > 1;
		
		// 1-12.
		this.next = this.endPage < realEndPage;
	}
}

/*
 * 
 	* 페이지네이션(전통적인 방법) -> 10페이지만 동작함
 	  | 현재  | 시작  | 끝   |
 	    1     1    10
 	    2     1    10
 	   10     1    10
 	   11    11    20
 	   13    11    20
 	   20    11    20
 	   
 	   Tip : 마지막 페이지부터 계산(페이지번호 갯수(10개)로 나눠서 계산 ceil 이용(올리기))
 
  
 **/

