package org.fekz115.dip.repository;

import org.fekz115.dip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    List<User> findByLoginOrEmail(String login, String email);
}
