package dinnerinmotion.authentication_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUser {

    String email;
    String password;
    Role role;
}
