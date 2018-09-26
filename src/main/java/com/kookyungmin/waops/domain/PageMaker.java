package com.kookyungmin.waops.domain;

import lombok.Data;

@Data
public class PageMaker {
	private int displayCount = 10;
	private Criteria cri;
	private boolean prev;
	private boolean next;
	private int startPage;
	private int endPage;
	private int totalCount;
	
	
	public PageMaker(Criteria cri, int totalCount) {
		this.cri = cri;
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = ((cri.getPage() - 1)/10 + 1) * displayCount;
		startPage = (endPage - displayCount) + 1;
		int tempEndPage = (int)Math.ceil(totalCount/(double)cri.getPerPageNum());
		if(tempEndPage < endPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = totalCount <= (endPage * cri.getPerPageNum()) ? false : true;	
	}
}
