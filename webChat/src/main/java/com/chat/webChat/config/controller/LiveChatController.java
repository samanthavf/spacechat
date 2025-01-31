package com.chat.webChat.config.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import com.chat.webChat.DTO.ChatInput;
import com.chat.webChat.DTO.ChatOutput;

@Controller
@CrossOrigin("*")
public class LiveChatController {

	@MessageMapping("/new-message")
	@SendTo("/topics/livechat")
	public ChatOutput newMessage(ChatInput input) {
		return new ChatOutput(HtmlUtils.htmlEscape(input.user() + ": " + input.message()));
	}
}
