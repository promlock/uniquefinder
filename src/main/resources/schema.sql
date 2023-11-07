
create table if not exists request_details(
  id bigserial not null,
  folder varchar(100) not null,
  user_name varchar(100) not null,
  request_date TIMESTAMP NOT NULL,
  PRIMARY KEY ( ID )
);

CREATE TABLE IF NOT EXISTS file_names(
    request_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_files_on_request FOREIGN KEY (request_id) REFERENCES request_details (id)
);