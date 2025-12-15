    package com.code.duel.code.duel.WebSocketConfiguration;
    
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
    import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

    @Configuration
    public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    
        @Override
        protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
            messages
                    .anyMessage().authenticated();
        }
        @Override
        protected boolean sameOriginDisabled() {
            // Disable CSRF for WebSocket connections
            return true;
        }
    }
