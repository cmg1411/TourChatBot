package com.chatbot.model;

import java.util.List;

import com.chatbot.model.Says;

import lombok.Data;

@Data
public class AjaxResponseBody {
	List<Says> msg;

}
