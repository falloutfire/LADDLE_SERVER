DROP TABLE IF EXISTS "point" CASCADE;
DROP TABLE IF EXISTS "laddle" CASCADE;
DROP TABLE IF EXISTS "property" CASCADE;
DROP TABLE IF EXISTS "refractory" CASCADE;
DROP TABLE IF EXISTS "shop" CASCADE;
DROP TABLE IF EXISTS "zone" CASCADE;

drop table if exists user_role CASCADE;
drop table if exists role CASCADE;
drop table if exists users CASCADE;

CREATE TABLE shop
(
    "id"   int         NOT NULL,
    "name" varchar(50) NOT NULL,
    PRIMARY KEY (id)

);

INSERT INTO shop(id, name)
VALUES (1, 'Пиздатый цех');


-- ************************************** "Ladle"

CREATE TABLE "laddle"
(
    "id"      int         NOT NULL,
    "name"    varchar(50) NOT NULL,
    "photo"   bytea       NOT NULL,
    "shop_id" int         NOT NULL,
    CONSTRAINT "FK_29" FOREIGN KEY ("shop_id") REFERENCES "shop" ("id"),
    PRIMARY KEY (id)
);

CREATE INDEX "fkIdx_29" ON "laddle"
    (
     "shop_id"
        );

INSERT INTO laddle(id, name, photo, shop_id)
VALUES (1, 'Пиздатый ladle', '1', 1);

CREATE TABLE "zone"
(
    "id"        int         NOT NULL,
    "name"      varchar(50) NOT NULL,
    "laddle_id" int         NOT NULL,
    CONSTRAINT "FK_37" FOREIGN KEY ("laddle_id") REFERENCES "laddle" ("id"),
    PRIMARY KEY (id)
);

CREATE INDEX "fkIdx_37" ON "zone"
    (
     "laddle_id"
        );

INSERT INTO zone(id, name, laddle_id)
VALUES (1, 'Пиздатый zone', 1);
-- ************************************** "Refractory"

CREATE TABLE "refractory"
(
    "id"      int         NOT NULL,
    "name"    varchar(50) NOT NULL,
    "zone_id" int         NOT NULL,
    CONSTRAINT "FK_44" FOREIGN KEY ("zone_id") REFERENCES "zone" ("id"),
    PRIMARY KEY (id)
);


CREATE INDEX "fkIdx_44" ON "refractory"
    (
     "zone_id"
        );

INSERT INTO refractory(id, name, zone_id)
VALUES (1, 'Пиздатый refractory', 1);
-- ************************************** "Property"

CREATE TABLE "property"
(
    "id"            int         NOT NULL,
    "name"          varchar(50) NOT NULL,
    "value"         varchar(50) NOT NULL,
    "type"          varchar(10) NOT NULL,
    "refractory_id" int         NOT NULL,
    CONSTRAINT "FK_53" FOREIGN KEY ("refractory_id") REFERENCES "refractory" ("id"),
    PRIMARY KEY (id)
);

CREATE INDEX "fkIdx_53" ON "property"
    (
     "refractory_id"
        );

INSERT INTO property(id, name, value, refractory_id, type)
VALUES (1, 'Пиздатый property', 1, 1, 'type');
-- ************************************** "Point"

CREATE TABLE "point"
(
    "id"      int NOT NULL,
    "x"       int NOT NULL,
    "y"       int NOT NULL,
    "zone_id" int NOT NULL,
    CONSTRAINT "FK_61" FOREIGN KEY ("zone_id") REFERENCES "zone" ("id"),
    PRIMARY KEY (id)
);

CREATE INDEX "fkIdx_61" ON "point"
    (
     "zone_id"
        );
INSERT INTO point(id, x, y, zone_id)
VALUES (1, 1, 1, 1);

CREATE TABLE role
(
    id          bigint NOT NULL,
    description varchar(255) DEFAULT NULL,
    role_name   varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE users
(
    id          bigint       NOT NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    middle_name varchar(255) NOT NULL,
    password    varchar(255) NOT NULL,
    username    varchar(255) NOT NULL,
    shop_id     bigint       NOT NULL,
    CONSTRAINT "FK_22" FOREIGN KEY ("shop_id") REFERENCES "shop" ("id"),
    PRIMARY KEY (id)
);
CREATE INDEX "fkIdx_22" ON "users"
    (
     "shop_id"
        );


CREATE TABLE user_role
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (id)
);

INSERT INTO role (id, role_name, description)
VALUES (1, 'ROLE_USER', 'Standard User - Has no admin rights');
INSERT INTO role (id, role_name, description)
VALUES (2, 'ROLE_ADMIN', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO users (id, first_name, last_name, password, username, middle_name, shop_id)
VALUES (1, 'Ilya', 'Man', '$2a$04$Cba8DVPiJDqJMTYVsGQmpuEnW4nVcfNke/d9XG0EOKPXyHZfbqXTC', 'user', 'Сергеич', 1);
INSERT INTO users (id, first_name, last_name, password, username, middle_name, shop_id)
VALUES (2, 'Admin', 'Admin', '$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u', 'admin', 'Валерыч', 1);


INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 2);

drop table if exists oauth_client_details;
create table oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);

drop table if exists oauth_client_details;
create table oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);
drop table if exists oauth_access_token;
create table oauth_access_token
(
    token_id          VARCHAR(255),
    token             bytea,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    bytea,
    refresh_token     VARCHAR(255)
);

create index oauth_access_token_id on oauth_access_token (token_id);
create index oauth_refresh_token_id on oauth_access_token (token_id);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          bytea,
    authentication bytea
);
drop table if exists oauth_code;
create table oauth_code
(
    code           VARCHAR(255),
    authentication bytea
);

drop table if exists oauth_approvals;
create table oauth_approvals
(
    userId         VARCHAR(255),
    clientId       VARCHAR(255),
    scope          VARCHAR(255),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
drop table if exists ClientDetails;
create table ClientDetails
(
    appId                  VARCHAR(255) PRIMARY KEY,
    resourceIds            VARCHAR(255),
    appSecret              VARCHAR(255),
    scope                  VARCHAR(255),
    grantTypes             VARCHAR(255),
    redirectUrl            VARCHAR(255),
    authorities            VARCHAR(255),
    access_token_validity  INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation  VARCHAR(4096),
    autoApproveScopes      VARCHAR(255)
);


-- insert client details
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity)
VALUES ('app_client', '$2a$12$fFEZR4h7MHrOTu1Bg/R.Eup.JYeULc8p9L9eCAU/pZDqTS5gI.i.S', 'read,write',
        'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2000);



