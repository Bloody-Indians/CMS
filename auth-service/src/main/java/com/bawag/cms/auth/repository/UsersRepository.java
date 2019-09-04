package com.bawag.cms.auth.repository;


import com.bawag.cms.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    Optional<User> findByFirstName(String firstname);
    Optional<User> findByUserEmail(String userEmail);

    User findByUsername(String username);
}
