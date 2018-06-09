package com.abc.blogger.util;

public class Comment {

	private Integer user_id;
	
	private String  tweet;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Comment(Integer user_id, String tweet) {
		super();
		this.user_id = user_id;
		this.tweet = tweet;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
}
