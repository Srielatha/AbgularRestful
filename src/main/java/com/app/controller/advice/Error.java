package com.app.controller.advice;

import java.util.ArrayList;
import java.util.List;

public class Error {

	private List<String> messages = new ArrayList<>();
	private String cause;

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}