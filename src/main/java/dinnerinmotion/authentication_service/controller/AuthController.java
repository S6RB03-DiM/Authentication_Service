package dinnerinmotion.authentication_service.controller;

import dinnerinmotion.authentication_service.exceptions.CouldNotCreateTokenException;
import dinnerinmotion.authentication_service.exceptions.EmailDoesNotExistException;
import dinnerinmotion.authentication_service.exceptions.NotAuthenticatedException;
import dinnerinmotion.authentication_service.exceptions.TokenIsEmptyException;
import dinnerinmotion.authentication_service.model.Login;
import dinnerinmotion.authentication_service.model.User;
import dinnerinmotion.authentication_service.service.AuthService;
import dinnerinmotion.authentication_service.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody Login login) throws NotAuthenticatedException, CouldNotCreateTokenException, EmailDoesNotExistException {
        return new ResponseEntity<String>(authService.signIn(login), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody String token) throws UnsupportedEncodingException, TokenIsEmptyException {
        if(token.isEmpty()){
            throw new TokenIsEmptyException();
        }
        jwtService.verifyToken(token);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
