CREATE TABLE IF NOT EXISTS file_names(
    request_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_files_on_request FOREIGN KEY (request_id) REFERENCES request_details (id)
);