package com.abc.blogger.util;

import java.util.Date;

public class Response {

	private Date timestamp;
	
	private String message;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
}
