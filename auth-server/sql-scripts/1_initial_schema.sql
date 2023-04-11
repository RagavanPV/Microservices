create table oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

CREATE TABLE seed_roles (
	id BIGINT auto_increment NOT NULL PRIMARY KEY,
	role_name varchar(100) NOT NULL,
	role_code varchar(100) NOT NULL
)

INSERT INTO seed_roles
	(role_name, role_code)
VALUES
	("ADMIN", "admin"),("USER", "user");

CREATE TABLE users (
	id BIGINT auto_increment NOT NULL PRIMARY KEY,
	username varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	email_id varchar(100) NOT NULL,
	activation_code varchar(100) NULL,
	activation varchar(100) NULL
)

CREATE TABLE user_roles (
	user_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL
)