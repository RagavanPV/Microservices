package com.ragavan.service;

import com.ragavan.constants.ClientConstants;
import com.ragavan.data.UserDAO;
import com.ragavan.model.CustomUserDetail;
import com.ragavan.model.User;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
    org.springframework.security.core.userdetails.User user =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
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

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
