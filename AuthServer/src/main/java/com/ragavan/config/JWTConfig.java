package com.ragavan.config;

import com.ragavan.service.CustomUserDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JWTConfig extends AuthorizationServerConfigurerAdapter {
  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private CustomUserDetailsService customUserDetailsService;

  @Autowired private DataSource dataSource;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
        .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED')")
        .checkTokenAccess("hasAuthority('ROLE_TRUSTED')");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(tokenStore())
        .tokenEnhancer(jwtAccessTokenConverter())
        .authenticationManager(authenticationManager)
        .userDetailsService(customUserDetailsService);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource);
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  // TODO encrypt password
  @Bean
  protected JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("123");
    return converter;
  }
}
