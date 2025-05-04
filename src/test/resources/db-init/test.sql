DROP TABLE TB_ARTICLE;

CREATE TABLE IF NOT EXISTS TB_ARTICLE(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    body VARCHAR(2000),
    author_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO TB_ARTICLE(title, body, author_id, created_at, updated_at) VALUES ('title 1', 'body 1', 1234, current_timestamp(), current_timestamp());