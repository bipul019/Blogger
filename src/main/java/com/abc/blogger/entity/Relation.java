package com.abc.blogger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="relation")
@IdClass(RelationPK.class)
public class Relation {

	@Id
	@Column(name="follower")
	@NotNull
	private int follower;

	@Id
	@Column(name="following")
	@NotNull
	private int following;



	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public Relation( @NotNull int follower, @NotNull int following) {
		super();
		
		this.follower = follower;
		this.following = following;
	}

	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
