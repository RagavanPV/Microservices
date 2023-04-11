package com.ragavan.authserver.data;

import com.ragavan.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String userName);
}
