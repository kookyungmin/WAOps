package com.kookyungmin.waops.domain;

import lombok.Data;

@Data
public class Criteria {
	private int page;
	private int perPageNum;
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}
}
