CREATE TABLE oauth2_authorization (
    id varchar(100) NOT NULL,
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorization_grant_type varchar(100) NOT NULL,
    authorized_scopes varchar(1000),
    attributes blob,
    state varchar(500),
    authorization_code_value blob,
    authorization_code_issued_at timestamp,
    authorization_code_expires_at datetime,
    authorization_code_metadata blob,
    access_token_value blob,
    access_token_issued_at datetime,
    access_token_expires_at datetime,
    access_token_metadata blob,
    access_token_type varchar(100),
    access_token_scopes varchar(1000),
    oidc_id_token_value blob,
    oidc_id_token_issued_at datetime,
    oidc_id_token_expires_at datetime,
    oidc_id_token_metadata blob,
    refresh_token_value blob,
    refresh_token_issued_at datetime,
    refresh_token_expires_at datetime,
    refresh_token_metadata blob,
    PRIMARY KEY (id)
);

CREATE TABLE oauth2_authorization_consent (
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorities varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200),
    client_secret_expires_at datetime,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000),
    post_logout_redirect_uris varchar(1000),
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);
