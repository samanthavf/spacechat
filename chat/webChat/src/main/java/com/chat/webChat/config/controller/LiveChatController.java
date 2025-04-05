package com.chat.webChat.config.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chat.webChat.DTO.ChatInput;

@Controller
@CrossOrigin("*")
public class LiveChatController {

	@MessageMapping("/new-message")
	@SendTo("/topics/livechat")
	public String newMessage(ChatInput input) {
		return (input.user() + ": " + input.message());
	}
}
