package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Mappers.RequestMapper.CreateMatchRequestMapper;
import com.code.duel.code.duel.Mappers.ResponseMapper.MatchStatusResponseMapper;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.MatchStatus;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {



    @Autowired
    MatchService matchService;
    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @PostMapping("/create")
    public ResponseEntity<Long> createMatch(
            @RequestBody CreateMatchRequestMapper request,
            @AuthenticationPrincipal User user) {
        Match match = matchService.createMatch(
                user.getUserID(),
                request.getDifficulty(),
                request.getProgrammingLanguage()
        );

        return ResponseEntity.ok(match.getMatchID());
    }

    @PostMapping("/join/{matchId}")
    public ResponseEntity<Long> joinMatch(@PathVariable Long matchId, @AuthenticationPrincipal User user) {
        Match joinedMatch = null;
        try {
            joinedMatch = matchService.joinMatch(matchId, user.getUserID());
        }catch (MatchNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(joinedMatch.getMatchID());
    }
    @GetMapping("/status/{matchId}/{userId}")
    public ResponseEntity<MatchStatusResponseMapper> getMatchStatus(@PathVariable Long matchId , @PathVariable Long userId){
        MatchStatusResponseMapper matchStatus = matchService.getMatchStatus(matchId , userId);
        if (matchStatus == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matchStatus);
    }

    @GetMapping("/matchesByUser")
    public ResponseEntity<List<Match>> getMatchesByUserId(@AuthenticationPrincipal User user) {
        List<Match> matches;
        try {
            matches = matchService.getMatchesByUserId(user.getUserID());
        }catch (MatchNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matches);
    }


}
