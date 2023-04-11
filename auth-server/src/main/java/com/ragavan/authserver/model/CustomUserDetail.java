package com.ragavan.authserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetail extends User implements UserDetails, Serializable {

  private static final long serialVersionUID = 1L;

  public CustomUserDetail(final User user) {
    super(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
