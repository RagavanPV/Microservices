package com.ragavan.config;

import com.ragavan.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends GlobalAuthenticationConfigurerAdapter {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private CustomUserDetailsService customUserDetailsService;

  @Override
  public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

    authenticationManagerBuilder
        .parentAuthenticationManager(authenticationManager)
        .userDetailsService(customUserDetailsService);
  }
}
