package com.ragavan.service;

import com.ragavan.constants.ClientConstants;
import com.ragavan.data.UserDAO;
import com.ragavan.model.CustomUserDetail;
import com.ragavan.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserDAO userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsernamePasswordAuthenticationToken authentication =
        (UsernamePasswordAuthenticationToken)
            SecurityContextHolder.getContext().getAuthentication();
    org.springframework.security.core.userdetails.User user =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    String userName = user.getUsername();
    if (ClientConstants.FRONTEND.equals(user.getUsername())) {
      Optional<User> userOptional = userDao.findByUserName(username);
      if (userOptional.isPresent()) {
        return userOptional.map(CustomUserDetail::new).get();
      } else {
        throw new UsernameNotFoundException("User name not found");
      }
    } else if (ClientConstants.ADMIN.equals(user.getUsername())) {
      Optional<User> userOptional = userDao.findByUserName(username);
      if (userOptional.isPresent()) {
        return userOptional.map(CustomUserDetail::new).get();
      } else {
        throw new UsernameNotFoundException("User name not found");
      }
    } else {
      throw new UsernameNotFoundException("User name not found");
    }
  }
}
