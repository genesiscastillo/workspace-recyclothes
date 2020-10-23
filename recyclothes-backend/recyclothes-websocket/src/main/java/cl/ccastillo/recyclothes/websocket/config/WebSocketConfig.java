package cl.ccastillo.recyclothes.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/recyclothesws")
    .setAllowedOrigins("*")
    .withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config){ 
    config.enableSimpleBroker("/topic/", "/queue/");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Bean
  public SocketSessionRegistry socketSessionRegistry(){
      return new SocketSessionRegistry();
  }
  @Bean
  public STOMPConnectEventListener stompConnectEventListener(){
      return new STOMPConnectEventListener();
  }
  @Bean
  public STOMPDisconnectEventListener stompDisconnectEventListener() {
	  return new STOMPDisconnectEventListener();
  }
}