create table report_category(
  report_id uuid not null,
  category_id uuid not null,

  primary key(report_id, category_id),

  foreign key(report_id) references report(id) on delete cascade,
  foreign key(category_id) references category(id) on delete cascade
);
