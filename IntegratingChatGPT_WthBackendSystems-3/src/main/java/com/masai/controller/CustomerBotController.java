package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.dto.ChatGPTRequest;
import com.masai.dto.ChatGPTResponse;

@RestController
public class CustomerBotController {

	    @Qualifier("openaiRestTemplate")
	    @Autowired
	    private RestTemplate restTemplate;
	    
	    @Value("${openai.model}")
	    private String model;
	    
	    @Value("${openai.api.url}")
	    private String apiUrl;
	    
	    @PostMapping("/chat")
	    public String chat(@RequestBody String prompt) {
	        // create a request
	    	ChatGPTRequest request = new ChatGPTRequest(model, prompt);
	        
	        // call the API
	    	ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
	        
	        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
	            return "No response";
	        }
	        
	        // return the first response
	        return response.getChoices().get(0).getMessage().getContent();
	    }
}
