package dinnerinmotion.authentication_service.controller;

import dinnerinmotion.authentication_service.exceptions.CouldNotCreateTokenException;
import dinnerinmotion.authentication_service.exceptions.EmailDoesNotExistException;
import dinnerinmotion.authentication_service.exceptions.NotAuthenticatedException;
import dinnerinmotion.authentication_service.exceptions.TokenIsEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity<String> handleNotAuthenticatedException(NotAuthenticatedException e) {
        log.error("User is not authenticated", e);
        return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleCouldNotCreateTokenException(CouldNotCreateTokenException e) {
        log.error("Could not create token", e);
        return new ResponseEntity<>("Could not create token", HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmailDoesNotExistException(EmailDoesNotExistException e) {
        log.error("Email does not exist", e);
        return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleTokenIsEmptyException(TokenIsEmptyException e) {
        log.error("Token is empty", e);
        return new ResponseEntity<>("Token is empty", HttpStatus.CONFLICT);
    }
}
