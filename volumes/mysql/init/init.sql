CREATE DATABASE kim DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE kim;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_im_user`;
CREATE TABLE `t_im_user`
(
    id        varchar(255)  not null
        primary key,
    avatar    varchar(255)  null,
    birthday  bigint        null,
    email     varchar(64)   null,
    extension varchar(1024) null,
    gender    int           null,
    mobile    varchar(15)   null,
    name      varchar(10)   null,
    password  varchar(32)   not null,
    signature varchar(255)  null,
    uid       varchar(32)   not null
);

DROP TABLE IF EXISTS `t_im_friend`;
CREATE TABLE `t_im_friend`
(
    id        varchar(255) not null
        primary key,
    friend_id varchar(255) not null,
    uid       varchar(255) not null
);

DROP TABLE IF EXISTS `t_im_message`;
CREATE TABLE `t_im_message`
(
    id        varchar(255)  not null
        primary key,
    content   varchar(5000) null,
    extra     varchar(255)  null,
    read_uids varchar(255)  null,
    receiver  varchar(255)  not null,
    scene     int           null,
    sender    varchar(255)  not null,
    sub_type  int           null,
    timestamp bigint        not null,
    title     varchar(255)  null,
    type      int           null
);

DROP TABLE IF EXISTS `t_im_session`;
CREATE TABLE `t_im_session`
(
    id          varchar(255) not null
        primary key,
    app_version varchar(255) null,
    bind_time   bigint       null,
    channel     varchar(10)  not null,
    device_id   varchar(64)  not null,
    device_name varchar(255) null,
    host        varchar(15)  not null,
    language    varchar(255) null,
    latitude    double       null,
    location    varchar(255) null,
    longitude   double       null,
    nid         varchar(32)  not null,
    os_version  varchar(255) null,
    state       int          null,
    uid         varchar(255) null
);

BEGIN;
INSERT INTO t_im_user (id, avatar, birthday, email, extension, gender, mobile, name, password, signature, uid) VALUES
('0', 'https://zhu-zichu.gitee.io/avatar.jpg', 798609512000, 'zhuzichu520@outlook.com', null, null,'18229858146', 'KIM小助手', '123456', '我是万能的小助手', 'admin');
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;