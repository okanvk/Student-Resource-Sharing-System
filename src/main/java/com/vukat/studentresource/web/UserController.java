package com.vukat.studentresource.web;

import com.vukat.studentresource.domain.User;
import com.vukat.studentresource.payload.JWTLoginSuccessResponse;
import com.vukat.studentresource.payload.LoginRequest;
import com.vukat.studentresource.security.JwtTokenProvider;
import com.vukat.studentresource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.vukat.studentresource.security.SecurityConstants.TOKEN_PREFIX;


@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){




        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginRequest.getUsername(),loginRequest.getPassword()
                        )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){

        userService.saveUser(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }



}
