INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('fooClientIdPassword', 'secret', 'foo,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);

INSERT INTO oauth_auth_database.users (username,password,email_id,activation_code,activation) VALUES
	 ('ragavanpv','password','rpitchai@presidio.com',NULL,NULL);

INSERT INTO oauth_auth_database.user_roles (user_id,role_id) VALUES
	 (1,1);
