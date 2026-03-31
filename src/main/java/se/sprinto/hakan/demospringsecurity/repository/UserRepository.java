package se.sprinto.hakan.demospringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sprinto.hakan.demospringsecurity.model.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
