DROP TABLE IF EXISTS user_info;
create table user_info(
   id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(10) NOT NULL,
   email VARCHAR(255) NOT NULL ,
   password VARCHAR(32) NOT NULL,
   mobile VARCHAR(15) NOT NULL,
   urls VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL,
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8 auto_increment=1;

DROP TABLE IF EXISTS oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(100) PRIMARY KEY,
  client_name VARCHAR(100),
  resource_ids VARCHAR(100),
  client_secret VARCHAR(100),
  scope VARCHAR(100),
  authorized_grant_types VARCHAR(100),
  web_server_redirect_uri VARCHAR(100),
  authorities VARCHAR(100),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(100),
  token blob,
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(100),
  token blob,
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100),
  authentication blob,
  refresh_token VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(100),
  token blob,
  authentication blob
);

DROP TABLE IF EXISTS oauth_code;
create table oauth_code (
  code VARCHAR(100), authentication blob
);

DROP TABLE IF EXISTS oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(100),
	clientId VARCHAR(100),
	scope VARCHAR(100),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

DROP TABLE IF EXISTS ClientDetails;
-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(100) PRIMARY KEY,
  resourceIds VARCHAR(100),
  appSecret VARCHAR(100),
  scope VARCHAR(100),
  grantTypes VARCHAR(100),
  redirectUrl VARCHAR(100),
  authorities VARCHAR(100),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(100)
);