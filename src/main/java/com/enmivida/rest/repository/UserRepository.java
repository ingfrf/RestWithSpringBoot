package com.enmivida.rest.repository;

import com.enmivida.rest.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("SELECT u FROM User u where u.userName =:userName")
    Optional<User> findByUserName(String userName);
}
