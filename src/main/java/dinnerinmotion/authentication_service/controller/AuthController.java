package dinnerinmotion.authentication_service.controller;

import dinnerinmotion.authentication_service.model.Login;
import dinnerinmotion.authentication_service.model.User;
import dinnerinmotion.authentication_service.service.AuthService;
import dinnerinmotion.authentication_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody Login login) {
        return new ResponseEntity<>("token", HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody String token) throws UnsupportedEncodingException {
        jwtService.verifyToken(token);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
