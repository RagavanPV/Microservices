package com.ragavan.config;

import com.ragavan.service.LoginAttemptService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Autowired private HttpServletRequest request;

  @Autowired private LoginAttemptService loginAttemptService;

  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent ev) {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      loginAttemptService.loginFailed(request.getRemoteAddr());
    } else {
      loginAttemptService.loginFailed(xfHeader.split(",")[0]);
    }
  }
}

@Component
class AuthenticationSuccessEventListener
    implements ApplicationListener<AuthenticationSuccessEvent> {
  @Autowired private HttpServletRequest request;

  @Autowired private LoginAttemptService loginAttemptService;

  public void onApplicationEvent(AuthenticationSuccessEvent e) {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      loginAttemptService.loginSucceeded(request.getRemoteAddr());
    } else {
      loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
    }
  }
}
