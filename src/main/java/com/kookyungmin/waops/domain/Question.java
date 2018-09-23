package com.kookyungmin.waops.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Question {
	private int qno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private int replycnt;
	private int lightcnt;
	private int score;
	private Date regdate;
	private Date updatedate;
}
