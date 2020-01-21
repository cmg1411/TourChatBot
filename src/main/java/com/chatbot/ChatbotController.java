package com.chatbot;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.common.collect.Maps;

import domain.AjaxResponseBody;
import domain.Mymes;
import domain.Says;
import com.chatbot.SaysRepository;


@RestController
public class ChatbotController {
	
	public static Map<String, QueryResult> detectIntentTexts(
			  String projectId, 
			  List<String> texts, 
			  String sessionId,
	      String languageCode) throws Exception {
		  Map<String, QueryResult> queryResults = Maps.newHashMap();
	    // Instantiates a client
	    try (SessionsClient sessionsClient = SessionsClient.create()) {
	      // Set the session name using the sessionId (UUID) and projectID (my-project-id)
	      SessionName session = SessionName.of(projectId, sessionId);
	      System.out.println("Session Path: " + session.toString());

	      // Detect intents for each text input
	      for (String text : texts) {
	        // Set the text (hello) and language code (en-US) for the query
	        Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

	        // Build the query with the TextInput
	        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

	        // Performs the detect intent request
	        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

	        // Display the query result
	        QueryResult queryResult = response.getQueryResult();

    System.out.println("====================");
    System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
    System.out.format("Detected Intent: %s (confidence: %f)\n",
        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
    System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

    queryResults.put(text, queryResult);      
	      }
	    }
		return queryResults;
	  }
	
	
	
	@Autowired
	private SaysRepository userService;
	
	
	@RequestMapping(value="/sendm", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<AjaxResponseBody> replyWrite(@Valid
			@RequestBody Mymes mes) {
		
		AjaxResponseBody result = new AjaxResponseBody(); 
		
		System.out.println("=====================================");
        System.out.println(mes.getInput());
        List<String> li = new ArrayList<String>();
	      li.add(mes.getInput());
	      try {
	         Map<String, QueryResult> map = detectIntentTexts("df-maven",li, "12345", "kr-KR");
	         
	         List<Says> says = userService.findALLByInput(map.get(li.get(0)).getFulfillmentText());
	         System.out.println(says);
	         result.setMsg(says);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
        return ResponseEntity.ok(result); 
    }
	
}
