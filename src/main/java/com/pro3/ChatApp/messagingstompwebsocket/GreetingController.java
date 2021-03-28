package com.pro3.ChatApp.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {
	@MessageMapping("/chat.send")
	 @SendTo("/topic/public")
	public Greeting sendMessage(@Payload Greeting greetMessage) {
        return greetMessage;
        
	 }
	@MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public Greeting newUser(@Payload Greeting greetMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", greetMessage.getSender());
        return greetMessage;
}
}

