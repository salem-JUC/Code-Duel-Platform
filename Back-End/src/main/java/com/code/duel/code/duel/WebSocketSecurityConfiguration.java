package com.code.duel.code.duel;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpDestMatchers("/**").permitAll()
                .simpSubscribeDestMatchers("/**").permitAll()
                .anyMessage().permitAll();
    }
    @Override
    protected boolean sameOriginDisabled() {
        // Disable CSRF for WebSocket connections
        return true;
    }
}
