package com.masai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {

	private String model;
    private List<Message> messages;
    private int n=1;
    private double temperature;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
