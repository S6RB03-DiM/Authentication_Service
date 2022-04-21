package dinnerinmotion.authentication_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID userId;

    @Column
    String email;

    @Column
    String password;
}
