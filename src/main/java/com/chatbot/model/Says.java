package com.chatbot.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Say")
public class Says {
	
	@Id
	private String id;	
	private String input;
	private String output;
	private String imgurl;
	private String url;
	
}
