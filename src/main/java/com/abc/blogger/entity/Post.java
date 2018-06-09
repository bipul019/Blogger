package com.abc.blogger.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private Integer post_id;
	
	@Column(name="user_id")
	@NotNull
	private Integer user_id;
	
	@Column(name="time")
	@NotNull
	private Date date;
	
	@Column(name="buzzcount")
	@NotNull
	private Integer buzzcount;
	
	@Column(name="comment")
	@NotNull
	private String comment;

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getBuzzcount() {
		return buzzcount;
	}

	public void setBuzzcount(Integer buzzcount) {
		this.buzzcount = buzzcount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post(Integer post_id, @NotNull Integer user_id, @NotNull Date date, @NotNull Integer buzzcount,
			@NotNull String comment) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.date = date;
		this.buzzcount = buzzcount;
		this.comment = comment;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
