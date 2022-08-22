package com.unibank.unitech.repository;

import com.unibank.unitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByPin(String pin);

}
