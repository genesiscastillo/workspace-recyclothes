package cl.ccastillo.recyclothes.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String simpSessionId = sha.getSessionId();
		System.out.println("-------    CONNECT [ "+simpSessionId+" ] -----------");
        webAgentSessionRegistry.registerSessionId(simpSessionId);
     }

}
