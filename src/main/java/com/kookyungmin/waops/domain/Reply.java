package com.kookyungmin.waops.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private Integer rno;
	private Integer qno;
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
}
