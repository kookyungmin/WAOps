package com.kookyungmin.waops.domain;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private String uid;
	private String upw;
	private String uname;
	private String email;
	private String naverId;
	private String googleId;
	private String nickname;
	private String loginip;
	private Date lastlogin;
	private String profile;
}
