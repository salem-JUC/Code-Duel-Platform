package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Mappers.RequestMapper.MatchCreationRequest;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.MatchService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class MatchWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MatchService matchService;
    private final Map<Long, Set<Long>> activeMatchSubscriptions = new ConcurrentHashMap<>();

    @Autowired
    public MatchWebSocketController(SimpMessagingTemplate messagingTemplate,
                                    MatchService matchService) {
        this.messagingTemplate = messagingTemplate;
        this.matchService = matchService;
    }

    // Create a new match


    // Handle code submission
//    @MessageMapping("/match/submit")
//    public void handleSubmission(CodeSubmission submission) {
//        try {
//            // Process submission through service layer
//            matchService.handleCorrectSubmission(
//                    submission.getMatchId(),
//                    submission.getPlayerId(),
//                    submission.getChallengeId()
//            );
//
//            // Get updated match status
//            MatchStatusResponseMapper status = matchService.getMatchStatus(
//                    submission.getMatchId(),
//                    submission.getPlayerId()
//            );
//
//            // Notify both players
//            notifyMatchPlayers(
//                    submission.getMatchId(),
//                    new MatchUpdate(
//                            "Player " + submission.getPlayerId() + " solved the challenge!",
//                            status.getMatch().getStatus(),
//                            status.getCurrentChallenge().getChallengeID(),
//                            Map.of(
//                                    status.getUserPlayMatch1().getUserID(), status.getUserPlayMatch1().getUserScore(),
//                                    status.getUserPlayMatch2().getUserID(), status.getUserPlayMatch2().getUserScore()
//                            )
//                    )
//            );
//
//        } catch (Exception e) {
//            messagingTemplate.convertAndSendToUser(
//                    submission.getPlayerId().toString(),
//                    "/queue/errors",
//                    new ErrorResponse("Submission failed: " + e.getMessage())
//            );
//        }
//    }

    // Subscribe to match updates
//    @SubscribeMapping("/topic/match/{matchId}")
//    public void subscribeToMatch(Long matchId, Long playerId) {
//        activeMatchSubscriptions
//                .computeIfAbsent(matchId, k -> ConcurrentHashMap.newKeySet())
//                .add(playerId);
//
//        // Send current match state to new subscriber
//        MatchStatusResponseMapper status = matchService.getMatchStatus(matchId, playerId);
//        messagingTemplate.convertAndSendToUser(
//                playerId.toString(),
//                "/queue/match/state",
//                status
//        );
//    }

    @MessageMapping("/match/{matchId}/wait")
    public void handleWaitingRoomSubscription(@DestinationVariable Long matchId, Principal principal) {

        // Check match status every second
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            if (matchService.isMatchReady(matchId)) {
                messagingTemplate.convertAndSend(
                        "/topic/match/" + matchId + "/ready",
                        matchId
                );
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

}
