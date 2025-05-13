package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Mappers.RequestMapper.UserRegisterRequest;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Repository.UserRepo;
import com.code.duel.code.duel.Service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


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
    Long idIncrement = 1000L;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegisterRequest request) {
        logger.info("Registering attempt new user with username: {}", request.getUsername());
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword("{noop}"+request.getPassword());
        newUser.setRole("PLAYER");
        newUser.setUserID(idIncrement);
        newUser.setScore(0);
        idIncrement++;
        try {
            userRepo.save(newUser);
        }catch (DataAccessException e){
            idIncrement--;
            logger.info("User registration failed with username: {}", request.getUsername());
            System.out.println(e.getMessage());
            return ResponseEntity.status(409).build();
        }

        logger.info("User registered successfully with username: {}", request.getUsername());
        return ResponseEntity.created(null).body(newUser);
    }


}
