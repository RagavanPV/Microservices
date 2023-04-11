package com.ragavan.authserver.service;

import com.ragavan.authserver.constants.ClientConstants;
import com.ragavan.authserver.data.UserDAO;
import com.ragavan.authserver.model.CustomUserDetail;
import com.ragavan.authserver.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserDAO userDao;

  @Autowired private LoginAttemptService loginAttemptService;

  @Autowired private HttpServletRequest request;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String ip = getClientIP();
    if (loginAttemptService.isBlocked(ip)) {
      throw new RuntimeException("blocked");
    }
    UsernamePasswordAuthenticationToken authentication =
        (UsernamePasswordAuthenticationToken)
            SecurityContextHolder.getContext().getAuthentication();
    Optional<User> userOptional = userDao.findByUserName(username);
    if (userOptional.isPresent()) {
      return userOptional.map(CustomUserDetail::new).get();
    } else {
      throw new UsernameNotFoundException("User name not found");
    }
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
