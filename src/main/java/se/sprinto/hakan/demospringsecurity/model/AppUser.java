package se.sprinto.hakan.demospringsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String username;
    private String password;
    private String role;

}
