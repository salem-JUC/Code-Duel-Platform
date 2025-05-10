package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Mappers.RequestMapper.SubmissionRequestMapper;
import com.code.duel.code.duel.Mappers.ResponseMapper.HitNotifcation;
import com.code.duel.code.duel.Mappers.ResponseMapper.MatchResult;
import com.code.duel.code.duel.Mappers.ResponseMapper.MatchStatusResponseMapper;
import com.code.duel.code.duel.Mappers.ResponseMapper.SubmissionResponse;
import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.MatchService;
import com.code.duel.code.duel.Service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class MatchWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MatchService matchService;
    private final SubmissionService submissionService;
    private final Map<Long, Set<Long>> activeMatchSubscriptions = new ConcurrentHashMap<>();

    @Autowired
    public MatchWebSocketController(SimpMessagingTemplate messagingTemplate,
                                    MatchService matchService,
                                    SubmissionService submissionService) {
        this.messagingTemplate = messagingTemplate;
        this.matchService = matchService;
        this.submissionService = submissionService;
    }

    @MessageMapping("/match/{matchId}/submit")
    public void handleCodeSubmission(@DestinationVariable Long matchId, @Payload SubmissionRequestMapper submission, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        System.out.println("Received submission from user: " + user.getUsername() + " for match ID: " + matchId);
        try {
            // 1. Validate the submission
            Submission result = submissionService.createSubmission(matchId, user.getUserID(), submission.getCode());
            System.out.println("Submission result: " + result);
            if (!result.getResult().equals("Accepted")) {
                System.out.println("Submission rejected: " + result);
                sendSubmissionResponse(principal.getName(), false, result.getResult());
                return;
            }
            processSuccessfulHit(matchId, user.getUserID(), result.getChallengeID());

        } catch (Exception e) {
            sendError(principal.getName(), "Submission error: " + e.getMessage());
        }
    }

    private void processSuccessfulHit(Long matchId, Long playerId, Long challengeId) {
        System.out.println("Processing successful hit for match ID: " + matchId + ", player ID: " + playerId + ", challenge ID: " + challengeId);
        // 1. Process the hit in service layer
        matchService.handleCorrectSubmmission(matchId, playerId, challengeId);

        // 2. Get updated match status
        MatchStatusResponseMapper status = matchService.getMatchStatus(matchId, playerId);

        // 3. Notify both players of the hit
        broadcastHit(matchId, playerId, status);

        // 4. Check if match ended
        if (status.getUserPlayMatch2().getUserScore() <= 0) {
            broadcastMatchEnd(matchId, playerId, status.getUserPlayMatch1().getUsername());
        } else {
            // 5. Assign and broadcast new challenge
            assignNewChallenge(matchId);
        }
    }

    private void assignNewChallenge(Long matchId) {
        matchService.assignChallenge(matchId);
        MatchStatusResponseMapper status = matchService.getMatchStatus(matchId, null);

        System.out.println("Assigning new challenge for match ID: " + matchId);
        // Broadcast new challenge to both players
        messagingTemplate.convertAndSend(
                "/topic/match/" + matchId + "/challenge",
                status.getCurrentChallenge()
        );
    }

    private void broadcastHit(Long matchId, Long hittingPlayerId,
                              MatchStatusResponseMapper status) {
        System.out.println("Broadcasting hit for match ID: " + matchId + ", hitting player ID: " + hittingPlayerId);
        messagingTemplate.convertAndSend(
                "/topic/match/" + matchId + "/hit",
                new HitNotifcation(
                        hittingPlayerId,
                        status.getUserPlayMatch1().getUserScore(),
                        status.getUserPlayMatch2().getUserScore()
                )
        );
    }

    private void broadcastMatchEnd(Long matchId, Long winnerId, String winnerName) {
        messagingTemplate.convertAndSend(
                "/topic/match/" + matchId + "/ended",
                new MatchResult(winnerId, winnerName)
        );

    }

    private void sendSubmissionResponse(String principalName, boolean accepted, String message) {
        System.out.println("Sending submission response to player ID: " + principalName + ", accepted: " + accepted);
        messagingTemplate.convertAndSendToUser(
                principalName,
                "/queue/submission",
                new SubmissionResponse(accepted, message)
        );
    }

    private void sendError(String principalName, String errorMessage) {
        messagingTemplate.convertAndSendToUser(
                principalName,
                "/queue/errors",
                errorMessage
        );
    }

    @MessageMapping("/match/{matchId}/ready")
    public void handlePlayerReady(@DestinationVariable Long matchId, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        MatchStatusResponseMapper status = matchService.getMatchStatus(matchId, user.getUserID());

        messagingTemplate.convertAndSendToUser(
                principal.getName(),
                "/queue/match/start",
                status
        );
    }
    @MessageMapping("/match/{matchId}/wait")
    public void handleWaitingRoomSubscription(@DestinationVariable Long matchId, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
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
