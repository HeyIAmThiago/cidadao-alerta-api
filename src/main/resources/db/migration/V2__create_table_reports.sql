create table report (
  id uuid not null,
  title varchar(80) not null,
  description text not null,
  image_url text,

  primary key(id)
);
