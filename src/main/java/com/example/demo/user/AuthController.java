package com.example.demo.user;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.UserAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final Logger logger = LogManager.getLogger();

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/sign_in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest signInRequest, HttpServletRequest request) {
        logger.debug("request = " + request.getRemoteAddr());
        logger.debug("request = " + request.getRemoteHost());
        logger.debug("request = " + request.getLocalAddr());
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }
//    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @PostMapping("/sign_up")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request) throws NoSuchAlgorithmException, DataNotFoundException, UserAlreadyExistException {
        logger.debug("request = " + request.getRemoteAddr());
        logger.debug("request = " + request.getRemoteHost());
        logger.debug("request = " + request.getLocalAddr());
        return ResponseEntity.ok(userService.signUp(signUpRequest));

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody TokensRequest tokensRequest, HttpServletRequest request){
        userService.logout(tokensRequest);
        return ResponseEntity.ok(null);
    }

}
