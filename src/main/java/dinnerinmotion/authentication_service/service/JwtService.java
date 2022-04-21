package dinnerinmotion.authentication_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dinnerinmotion.authentication_service.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Service
public class JwtService
{
    @Value("${jwtSecret}")
    private String jwtSecret;

    public String createToken(User user) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("AuthenticationService")
                .withClaim("userId", user.getUserId().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }


    public String verifyToken(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("AuthenticationService")
                .build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("userId").asString();
    }
}