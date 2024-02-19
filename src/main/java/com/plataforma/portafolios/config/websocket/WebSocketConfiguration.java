package com.plataforma.portafolios.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


    //it allows the communication using a broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //You can access to the broker using this path
        registry.enableSimpleBroker("/topic");
        //put the message destination
        registry.setApplicationDestinationPrefixes("/app");
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //it allows frontend to connect with the path below
        registry.addEndpoint("/chat-socket")
                    //My angular client need to be allowed
                .setAllowedOrigins("http://localhost:4200")
                //a library we have to use in frontend
                .withSockJS();
    }
}
