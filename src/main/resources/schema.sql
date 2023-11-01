
create table if not exists request_details(
  id bigserial not null,
  folder varchar(100) not null,
  user_name varchar(100) not null,
  request_date TIMESTAMP NOT NULL,
  PRIMARY KEY ( ID )
);