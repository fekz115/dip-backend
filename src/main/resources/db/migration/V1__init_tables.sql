create sequence hibernate_sequence;

create table address (
       id int4 not null,
        building varchar(255),
        location_id int4,
        street_id int4,
        primary key (id)
);

create table article (
   id int4 not null,
    can_be_commented boolean not null,
    can_be_rated boolean not null,
    publication_date timestamp,
    show_author boolean not null,
    title varchar(255),
    author_id int4,
    body_id int4,
    primary key (id)
);

create table article_comments (
   article_id int4 not null,
    comments_id int4 not null
);

create table article_dislikes (
   article_id int4 not null,
    dislikes_id int4 not null,
    primary key (article_id, dislikes_id)
);

create table article_likes (
   article_id int4 not null,
    likes_id int4 not null,
    primary key (article_id, likes_id)
);

create table article_tags (
   article_id int4 not null,
    tags_id int4 not null,
    primary key (article_id, tags_id)
);

create table city (
   id int4 not null,
    name varchar(255),
    country_id int4,
    primary key (id)
);

create table comment (
   id int4 not null,
    publication_date timestamp,
    content_body_id int4,
    reply_to_id int4,
    user_id int4,
    primary key (id)
);

create table comment_dislikes (
   comment_id int4 not null,
    dislikes_id int4 not null,
    primary key (comment_id, dislikes_id)
);

create table comment_likes (
   comment_id int4 not null,
    likes_id int4 not null,
    primary key (comment_id, likes_id)
);

create table content_body (
   id int4 not null,
    primary key (id)
);

create table content_body_content (
   content_body_id int4 not null,
    content_id int4 not null
);

create table content_container (
   id int4 not null,
    index int4 not null,
    music_id int4,
    picture_id int4,
    text_id int4,
    video_id int4,
    primary key (id)
);

create table country (
   id int4 not null,
    name varchar(255),
    primary key (id)
);

create table event (
   id int4 not null,
    article_id int4,
    primary key (id)
);

create table event_locations (
   event_id int4 not null,
    locations_id int4 not null
);

create table event_location (
   id int4 not null,
    finish_date timestamp,
    start_date timestamp,
    address_id int4,
    primary key (id)
);

create table location (
   id int4 not null,
    latitude float8 not null,
    longitude float8 not null,
    primary key (id)
);

create table music (
   id int4 not null,
    file_name varchar(255),
    name varchar(255),
    primary key (id)
);

create table picture (
   id int4 not null,
    file_name varchar(255),
    name varchar(255),
    primary key (id)
);

create table street (
   id int4 not null,
    name varchar(255),
    city_id int4,
    primary key (id)
);

create table tag (
   id int4 not null,
    name varchar(255),
    primary key (id)
);

create table text (
   id int4 not null,
    bold boolean not null,
    color int4 not null,
    data varchar(255),
    italic boolean not null,
    link varchar(255),
    separate boolean not null,
    size int4 not null,
    strike boolean not null,
    primary key (id)
);

create table user_info (
   id int4 not null,
    first_name varchar(255),
    last_name varchar(255),
    address_id int4,
    picture_id int4,
    primary key (id)
);

create table user_role (
   user_id int4 not null,
    roles varchar(255)
);

create table usr (
   id int4 not null,
    activation_code varchar(255),
    active boolean not null,
    banned boolean not null,
    email varchar(255),
    login varchar(255),
    password varchar(255),
    user_info_id int4,
    primary key (id)
);

create table video (
   id int4 not null,
    file_name varchar(255),
    name varchar(255),
    primary key (id)
);