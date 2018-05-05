package com.ragavan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails {
  @Id
  @Column(name = "client_id")
  private String clientId;

  @Column(name = "resource_ids")
  private String resourceIds;

  @Column(name = "client_secret")
  private String clientSecret;

  @Column(name = "scope")
  private String scope;

  @Column(name = "authorized_grant_types")
  private String authorizedGrantTypes;

  @Column(name = "web_server_redirect_uri")
  private String webServerRedirectUri;

  @Column(name = "authorities")
  private String authorities;

  @Column(name = "access_token_validity")
  private int accessTokenValidity;

  @Column(name = "refresh_token_validity")
  private int refreshTokenValidity;

  @Column(name = "additional_information")
  private String additionalInformation;

  @Column(name = "autoapprove")
  private boolean autoApprove;

  public OauthClientDetails() {}

  public OauthClientDetails(OauthClientDetails clientDetails) {
    this.clientId = clientDetails.getClientId();
    this.resourceIds = clientDetails.getResourceIds();
    this.clientSecret = clientDetails.getClientSecret();
    this.scope = clientDetails.getScope();
    this.authorizedGrantTypes = clientDetails.getAuthorizedGrantTypes();
    this.webServerRedirectUri = clientDetails.getWebServerRedirectUri();
    this.authorities = clientDetails.getAuthorities();
    this.accessTokenValidity = clientDetails.getAccessTokenValidity();
    this.refreshTokenValidity = clientDetails.getRefreshTokenValidity();
    this.additionalInformation = clientDetails.getAdditionalInformation();
    this.autoApprove = clientDetails.isAutoApprove();
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(String resourceIds) {
    this.resourceIds = resourceIds;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getAuthorizedGrantTypes() {
    return authorizedGrantTypes;
  }

  public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
    this.authorizedGrantTypes = authorizedGrantTypes;
  }

  public String getWebServerRedirectUri() {
    return webServerRedirectUri;
  }

  public void setWebServerRedirectUri(String webServerRedirectUri) {
    this.webServerRedirectUri = webServerRedirectUri;
  }

  public String getAuthorities() {
    return authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }

  public int getAccessTokenValidity() {
    return accessTokenValidity;
  }

  public void setAccessTokenValidity(int accessTokenValidity) {
    this.accessTokenValidity = accessTokenValidity;
  }

  public int getRefreshTokenValidity() {
    return refreshTokenValidity;
  }

  public void setRefreshTokenValidity(int refreshTokenValidity) {
    this.refreshTokenValidity = refreshTokenValidity;
  }

  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public boolean isAutoApprove() {
    return autoApprove;
  }

  public void setAutoApprove(boolean autoApprove) {
    this.autoApprove = autoApprove;
  }
}
