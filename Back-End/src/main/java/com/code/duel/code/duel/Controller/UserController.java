package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Mappers.ResponseMapper.UserStatics;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    MatchService matchService;
    @GetMapping("/statics")
    public ResponseEntity<UserStatics> getUserProfile(@AuthenticationPrincipal User user) {
        UserStatics userStatics = new UserStatics();
        try {
            userStatics.setWinLoseRatio(matchService.getWinLoseRationByUserId(user.getUserID()));
            userStatics.setTotalMatches(matchService.getMatchesByUserId(user.getUserID()).size());
        }catch (MatchNotFoundException e){
            userStatics.setTotalMatches(0);
        }
        return ResponseEntity.ok(userStatics);
    }
}
