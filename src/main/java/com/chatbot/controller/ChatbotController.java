package com.chatbot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.model.AjaxResponseBody;
import com.chatbot.model.Mymes;
import com.chatbot.model.Says;
import com.chatbot.repository.SaysRepository;
import com.chatbot.service.DetectIntent;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.common.collect.Maps;

@RestController
public class ChatbotController {

	@Autowired
	private SaysRepository userService;
	
	@PostMapping(value = "/sendm")
	public @ResponseBody ResponseEntity<AjaxResponseBody> replyWrite(@Valid @RequestBody Mymes mes) {

		AjaxResponseBody result = new AjaxResponseBody();

		System.out.println("=====================================");
		System.out.println(mes.getInput());
		
		List<String> li = new ArrayList<String>();
		li.add(mes.getInput());
		
		try {
			Map<String, QueryResult> map = DetectIntent.detectIntentTexts("df-maven", li, "12345", "kr-KR");

			List<Says> says = userService.findALLByInput(map.get(li.get(0)).getFulfillmentText());
			System.out.println(says);
			result.setMsg(says);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(result);
	}

}
