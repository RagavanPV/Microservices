package com.ragavan.service;

import com.ragavan.data.UserDAO;
import com.ragavan.model.CustomUserDetail;
import com.ragavan.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserDAO userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOptional = userDao.findByUserName(username);
    if (userOptional.isPresent()) {
      return userOptional.map(CustomUserDetail::new).get();
    } else {
      throw new UsernameNotFoundException("User name not found");
    }
  }
}
