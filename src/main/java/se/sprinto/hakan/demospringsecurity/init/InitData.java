package se.sprinto.hakan.demospringsecurity.init;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.sprinto.hakan.demospringsecurity.model.AppUser;
import se.sprinto.hakan.demospringsecurity.repository.UserRepository;

@Component
public class InitData {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createUser() {
        AppUser appUser = new AppUser();
        appUser.setPassword(passwordEncoder.encode("password"));
        appUser.setRole("ADMIN");
        appUser.setUsername("hakan.gleissman@gmail.com");
        //userRepository.save(appUser);


    }
}
