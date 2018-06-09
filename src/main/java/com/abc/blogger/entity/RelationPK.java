package com.abc.blogger.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RelationPK implements Serializable{

	private Integer follower;
	
	private Integer following;

	public Integer getFollower() {
		return follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public Integer getFollowing() {
		return following;
	}

	public void setFollowing(Integer following) {
		this.following = following;
	}
}
