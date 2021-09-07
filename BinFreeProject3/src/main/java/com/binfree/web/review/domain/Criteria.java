package com.binfree.web.review.domain;

import lombok.Data;

@Data
public class Criteria {
	/* 현재 페이지 */
	private int pageNum;

	/* 한 페이지 당 보여질 게시물 갯수 */
	private int amount;

	/* 스킵 할 게시물 수( (pageNum-1) * amount ) */
	private int skip;

	/* 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10 */
	public Criteria() {
		this(1, 5);
		this.skip = 0;
	}

	/* 생성자 => 원하는 pageNum, 원하는 amount */
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public void setPageNum(int pageNum) {
		this.skip = (pageNum-1)*this.amount;
		this.pageNum = pageNum;
		
	}
	
	public void setAmount(int amount) {
		this.skip = (pageNum-1)*this.amount;
		this.amount = amount;
		
	}
	
}
