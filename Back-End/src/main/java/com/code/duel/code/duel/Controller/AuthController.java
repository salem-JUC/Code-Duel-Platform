package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {

        User user;
        try {
            user = (User) authentication.getPrincipal();
        }catch (NullPointerException e){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(user);
    }


}
