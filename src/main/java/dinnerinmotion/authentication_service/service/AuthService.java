package dinnerinmotion.authentication_service.service;

import dinnerinmotion.authentication_service.exceptions.CouldNotCreateTokenException;
import dinnerinmotion.authentication_service.exceptions.EmailDoesNotExistException;
import dinnerinmotion.authentication_service.exceptions.NotAuthenticatedException;
import dinnerinmotion.authentication_service.exceptions.TokenIsEmptyException;
import dinnerinmotion.authentication_service.model.Login;
import dinnerinmotion.authentication_service.model.User;
import dinnerinmotion.authentication_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepo;

    public String signIn(Login login) throws NotAuthenticatedException, CouldNotCreateTokenException, EmailDoesNotExistException {
        User user;
        try{
            user = userRepo.findByEmail(login.getEmail());
            if (login.getEmail().equals(user.getEmail()) && DecodePassword(login.getPassword(), user.getPassword())) {
                return createToken(user);
            }
            else{
                throw new NotAuthenticatedException();
            }
        } catch (Exception e){
            throw new EmailDoesNotExistException();
        }
    }

    public String createToken(User user) throws CouldNotCreateTokenException {
        String token;
        try {
            token = jwtService.createToken(user);
            if(token.isEmpty()){
                throw new TokenIsEmptyException();
            }
            return token;
        }
        catch(Exception e){
            throw new CouldNotCreateTokenException();
        }
    }

    public String EncodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    public boolean DecodePassword(String loginPassword, String userPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(loginPassword, userPassword);
    }

    //TODO: SignUp & Encoding password
//    public User signUp(Login login) {
//       return User;
//    }
}
