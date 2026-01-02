package com.code.duel.code.duel.WebSocketConfiguration;


import com.code.duel.code.duel.Controller.MatchWebSocketController;
import com.code.duel.code.duel.Mappers.ResponseMapper.MatchResult;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Model.UserPlayMatch;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Service.MatchService;
import com.code.duel.code.duel.Service.PendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WebSocketEventListener {


    private final SimpMessagingTemplate messagingTemplate;
    private final MatchService matchService;

    private final UserPlayMatchRepo userPlayMatchRepo;
    private final SimpUserRegistry simpUserRegistry;
    private final PendingService pendingService;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate
                                  , MatchService matchService
                                    , UserPlayMatchRepo userPlayMatchRepo
                                  , SimpUserRegistry simpUserRegistry, PendingService pendingService) {
        this.messagingTemplate = messagingTemplate;
        this.matchService = matchService;
        this.userPlayMatchRepo = userPlayMatchRepo;
        this.simpUserRegistry = simpUserRegistry;
        this.pendingService = pendingService;
    }
    @EventListener
    public void handleSessionConnect(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String type = headerAccessor.getFirstNativeHeader("type");
        headerAccessor.getSessionAttributes().put("type", type);
        if (type.equals("MATCH")){
            Long matchId = Long.parseLong(headerAccessor.getFirstNativeHeader("matchId"));
            Long userId = Long.parseLong(headerAccessor.getFirstNativeHeader("userId"));
            System.out.println("Match ID: " + matchId + ", User ID: " + userId );
            headerAccessor.getSessionAttributes().put("matchId", matchId);
            headerAccessor.getSessionAttributes().put("userId", userId);
            logger.info("User {} and Match {} connect from match socket" , userId , matchId);
            pendingService.removeIfPending(userId);
        } else if (type.equals("WAITING")) {
            System.out.println("Waiting WebSocket connection established" + " set sessions attributes");
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = event.getSessionId();
        String type =(String) headerAccessor.getSessionAttributes().get("type");
        System.out.println("WebSocket disconnection with type: " + type);
        if (type.equals("MATCH")){
            System.out.println("Match WebSocket disconnection established" + " get sessions attributes");
            Long matchId = (Long) headerAccessor.getSessionAttributes().get("matchId");
            Long userId = (Long) headerAccessor.getSessionAttributes().get("userId");
            logger.info("User {} and Match {} just disconnect from match socket" , userId , matchId);
            pendingService.markAsPending(userId);
        } else if (type.equals("WAITING")) {

        }
    }
}
