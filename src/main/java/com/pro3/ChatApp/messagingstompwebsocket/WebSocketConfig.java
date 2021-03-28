package com.pro3.ChatApp.messagingstompwebsocket;


import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    
		public void registerStompEndpoints(final StompEndpointRegistry registry) {
			registry.addEndpoint("/ws").withSockJS();
		
	}

	public void configureMessageBroker(final MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	

	
}
