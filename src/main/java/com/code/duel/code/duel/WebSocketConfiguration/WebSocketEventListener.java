package com.code.duel.code.duel.WebSocketConfiguration;


import com.code.duel.code.duel.Mappers.ResponseMapper.MatchResult;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Model.UserPlayMatch;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Service.MatchService;
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
    private MatchService matchService;

    private UserPlayMatchRepo userPlayMatchRepo;
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate
                                  , MatchService matchService
                                    , UserPlayMatchRepo userPlayMatchRepo
                                  , SimpUserRegistry simpUserRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.matchService = matchService;
        this.userPlayMatchRepo = userPlayMatchRepo;
        this.simpUserRegistry = simpUserRegistry;
    }
    @EventListener
    public void handleSessionConnect(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String type = (String) headerAccessor.getFirstNativeHeader("type");
        System.out.println("WebSocket connection established with type: " + type);
        headerAccessor.getSessionAttributes().put("type", type);
        if (type.equals("MATCH")){
            System.out.println("Match WebSocket connection established" + " set sessions attributes");
            Long matchId = Long.parseLong(headerAccessor.getFirstNativeHeader("matchId"));
            Long userId = Long.parseLong(headerAccessor.getFirstNativeHeader("userId"));
            System.out.println("Match ID: " + matchId + ", User ID: " + userId );
            headerAccessor.getSessionAttributes().put("matchId", matchId);
            headerAccessor.getSessionAttributes().put("userId", userId);
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
        boolean isMatchFinished = matchService.isMatchFinished((Long) headerAccessor.getSessionAttributes().get("matchId"));
        if (type.equals("MATCH") && isMatchFinished == false){
            System.out.println("Match WebSocket disconnection established" + " get sessions attributes");
            Long matchId = (Long) headerAccessor.getSessionAttributes().get("matchId");
            Long userId = (Long) headerAccessor.getSessionAttributes().get("userId");
            String username = userPlayMatchRepo.findByUserIDAndMatchID(userId, matchId).getUsername();
            UserPlayMatch opponent = userPlayMatchRepo.findTheOpponent(userId , matchId);
            System.out.println("Match ID: " + matchId + ", User ID: " + userId + ", Opponent ID: " + opponent.getUserID() + ", Opponent Username: " + opponent.getUsername());
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            // Schedule the end of the match after 5 seconds
            scheduler.schedule(() -> {
                SimpUser simpUser = simpUserRegistry.getUser(username);
                if (simpUser == null){
                    System.out.println("User " + username + " has no active sessions, ending match.");
                    matchService.endMatch(matchId, opponent.getUserID());
                    // Broadcasting Match End
                    messagingTemplate.convertAndSend(
                            "/topic/match/" + matchId + "/ended",
                            new MatchResult(opponent.getUserID(), opponent.getUsername())
                    );
                } else {
                    System.out.println("User " + username + " is still connected, not ending match.");
                }

            }, 5, TimeUnit.SECONDS);
        } else if (type.equals("WAITING")) {
            System.out.println("Waiting WebSocket disconnection established" + " get sessions attributes");
        }
    }
}
