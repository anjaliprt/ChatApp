package com.pro3.ChatApp.messagingstompwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

//import com.pro3.ChatApp.messagingstompwebsocket.Greeting.MessageType;



public class WebSocketEventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

	//private static final MessageType LEAVE = null;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    	LOGGER.info("Wohooo! We have a new connection");
    }
    
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    	final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        final String username = (String) headerAccessor.getSessionAttributes().get("username");
       
		Greeting greetMessage = new Greeting();
		greetMessage.setType(Greeting.MessageType.LEAVE);
		greetMessage.setSender(username);

        messagingTemplate.convertAndSend("/topic/public", greetMessage);
    

}
}