DROP TABLE IF EXISTS point CASCADE;
DROP TABLE IF EXISTS laddle CASCADE;
DROP TABLE IF EXISTS property CASCADE;
DROP TABLE IF EXISTS refractory CASCADE;
DROP TABLE IF EXISTS shop CASCADE;
DROP TABLE IF EXISTS zone CASCADE;

drop table if exists user_role CASCADE;
drop table if exists role CASCADE;
drop table if exists users CASCADE;

create table shop
(
    id               bigint not null
        constraint shop_pkey
            primary key,
    employees_number integer,
    name             varchar(255)
);

INSERT INTO shop(id, name, employees_number)
VALUES (1, 'Цех номер 1', 100);


-- ************************************** "Ladle"

create table laddle
(
    id      bigint not null
        constraint laddle_pkey
            primary key,
    name    varchar(255),
    photo   bytea,
    shop_id bigint not null
        constraint fkenejp0s83ufpfixrdwy55cdbi
            references shop
);

INSERT INTO laddle(id, name, photo, shop_id)
VALUES (1, 'Ковш 1', '1', 1);

create table zone
(
    id        bigint not null
        constraint zone_pkey
            primary key,
    name      varchar(255),
    laddle_id bigint not null
        constraint fk70pqwyx7m4uie64wul8m8sn6r
            references laddle
);

INSERT INTO zone(id, name, laddle_id)
VALUES (1, 'Зона 1', 1);
-- ************************************** "Refractory"

create table refractory
(
    id      bigint not null
        constraint refractory_pkey
            primary key,
    name    varchar(255),
    zone_id bigint not null
        constraint fkpw1wys9pfjqw19id8wwh57kgq
            references zone
);

INSERT INTO refractory(id, name, zone_id)
VALUES (1, 'Огнеупор', 1);
-- ************************************** "Property"

create table property
(
    id            bigint not null
        constraint property_pkey
            primary key,
    name          varchar(255),
    type          varchar(255),
    value         varchar(255),
    refractory_id bigint not null
        constraint fkjfyll53w6wir63d59x7qbaovd
            references refractory
);

INSERT INTO property(id, name, value, refractory_id, type)
VALUES (1, 'Свойство', 1, 1, 'Тип');
-- ************************************** "Point"

create table point
(
    id      bigint not null
        constraint point_pkey
            primary key,
    x       integer,
    y       integer,
    zone_id bigint not null
        constraint fk5nh147hk25f3fxt8n9187qowy
            references zone
);

INSERT INTO point(id, x, y, zone_id)
VALUES (1, 1, 1, 1);


create table role
(
    id          bigserial not null
        constraint role_pkey
            primary key,
    description varchar(255),
    role_name   varchar(255)
);


create table users
(
    id          bigint not null
        constraint users_pkey
            primary key,
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    username    varchar(255),
    password    varchar(255),
    shop_id     bigint
        constraint fkfesbkulej4bo8neiy68turcbo
            references shop
);

create table user_role
(
    user_id bigint not null
        constraint fkj345gk1bovqvfame88rcx7yyx
            references users,
    role_id bigint not null
        constraint fka68196081fvovjhkek5m97n3y
            references role
);

INSERT INTO role (id, role_name, description)
VALUES (1, 'ROLE_USER', 'Standard User - Has no admin rights');
INSERT INTO role (id, role_name, description)
VALUES (2, 'ROLE_ADMIN', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO users (id, first_name, last_name, password, username, middle_name, shop_id)
VALUES (1, 'Ilya', 'Man', '$2a$04$Cba8DVPiJDqJMTYVsGQmpuEnW4nVcfNke/d9XG0EOKPXyHZfbqXTC', 'user', '_', 1);
INSERT INTO users (id, first_name, last_name, password, username, middle_name, shop_id)
VALUES (2, 'Admin', 'Admin', '$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u', 'admin', '_', 1);


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



