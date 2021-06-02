alter table event add column
    poster_id int4;

alter table if exists event
   add constraint event_poster
   foreign key (poster_id)
   references picture;