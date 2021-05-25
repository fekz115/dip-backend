alter table if exists article_comments
   add constraint UK_3p3q8lmg108o11h9tix5mdfb3 unique (comments_id);

alter table if exists content_body_content
   add constraint UK_rvialf3l6ynyudqtl9sg2ee67 unique (content_id);

alter table if exists event_locations
   add constraint UK_yktx78dkl5kq8qeywdl5k0ty unique (locations_id);

alter table if exists address
   add constraint FK3sbwy3lw9ts4hybjslnp1ikri
   foreign key (location_id)
   references location;

alter table if exists address
   add constraint FKft0pt0eni21ptl8s37dtq8s9
   foreign key (street_id)
   references street;

alter table if exists article
   add constraint FKlkpqxhd1wrf0tn47tw1e58hcd
   foreign key (author_id)
   references usr;

alter table if exists article
   add constraint FKkeiodxcd0nu5s3b9odmkjncwd
   foreign key (body_id)
   references content_body;

alter table if exists article_comments
   add constraint FKpodmjhlfsry50qirst9nv0q38
   foreign key (comments_id)
   references comment;

alter table if exists article_comments
   add constraint FKhdo7dtp0o8cn5wo7j1cs1gokg
   foreign key (article_id)
   references article;

alter table if exists article_dislikes
   add constraint FKe0rh7nrwtlf2ig186o2dgirci
   foreign key (dislikes_id)
   references usr;

alter table if exists article_dislikes
   add constraint FK5436kfen2yfl120na13ao22x9
   foreign key (article_id)
   references article;

alter table if exists article_likes
   add constraint FKol9fl26k0tpxbo27aao672fso
   foreign key (likes_id)
   references usr;

alter table if exists article_likes
   add constraint FK1wt0ww82gfxkuxw3ghxmp55xy
   foreign key (article_id)
   references article;

alter table if exists article_tags
   add constraint FKp6owh2p5p9yllwwrc2hn7bnxr
   foreign key (tags_id)
   references tag;

alter table if exists article_tags
   add constraint FK85ph188kqbfc5u1gq0tme7flk
   foreign key (article_id)
   references article;

alter table if exists city
   add constraint FKrpd7j1p7yxr784adkx4pyepba
   foreign key (country_id)
   references country;

alter table if exists comment
   add constraint FK5k2o0jjr1ydr7abifbkxr205i
   foreign key (content_body_id)
   references content_body;

alter table if exists comment
   add constraint FKqys9mdo2xp2p1848yk002bw8e
   foreign key (reply_to_id)
   references comment;

alter table if exists comment
   add constraint FKgcgdcgly6u49hf4g8y2di3g4p
   foreign key (user_id)
   references usr;

alter table if exists comment_dislikes
   add constraint FK8btjkr9yhlfhn1t8aam7t9gt1
   foreign key (dislikes_id)
   references usr;
alter table if exists comment_dislikes
   add constraint FKhtnn43n3lworilxuedc6swwvi
   foreign key (comment_id)
   references comment;
alter table if exists comment_likes
   add constraint FKfacf2w1ogw6pc1n102igo1sd8
   foreign key (likes_id)
   references usr;
alter table if exists comment_likes
   add constraint FKd0epu3dcjc57pwe7lt5jgfqsi
   foreign key (comment_id)
   references comment;

alter table if exists content_body_content
   add constraint FKks1svd2mr52s6rt3arxkg291o
   foreign key (content_id)
   references content_container;

alter table if exists content_body_content
   add constraint FKnap6kpt4tacjar2awy962yg89
   foreign key (content_body_id)
   references content_body;

alter table if exists content_container
   add constraint FK5ve41uq38om8yoyuc6niblmrw
   foreign key (music_id)
   references music;

alter table if exists content_container
   add constraint FKoyimewcd0rbyfmyacwvj9xv09
   foreign key (picture_id)
   references picture;

alter table if exists content_container
   add constraint FK8infsreaq45wuyj1vbfdbinls
   foreign key (text_id)
   references text;

alter table if exists content_container
   add constraint FKpjm18amoghrgshb61qj0hkp6l
   foreign key (video_id)
   references video;

alter table if exists event
   add constraint FK2wcvo61kw2vkguws07sbmaq68
   foreign key (article_id)
   references article;

alter table if exists event_locations
   add constraint FKt3mi9xashdocr7triw07exn2l
   foreign key (locations_id)
   references event_location;

alter table if exists event_locations
   add constraint FKnau042bqq2j80lvt1u561tydg
   foreign key (event_id)
   references event;

alter table if exists event_location
   add constraint FKsoe1qlpcmbnt6dcf5pq1jiy8q
   foreign key (address_id)
   references address;

alter table if exists street
   add constraint FKacmccbd831lu9xq9plvwbc4y7
   foreign key (city_id)
   references city;

alter table if exists user_info
   add constraint FKnoqtjbqb8et14k5f54flsdram
   foreign key (address_id)
   references address;

alter table if exists user_info
   add constraint FKe1ilpymvyn7t8uf4ti9y293js
   foreign key (picture_id)
   references picture;

alter table if exists user_role
   add constraint FKfpm8swft53ulq2hl11yplpr5
   foreign key (user_id)
   references usr;

alter table if exists usr
   add constraint FKb56uwbvbwhu894wuh154nypw0
   foreign key (user_info_id)
   references user_info;