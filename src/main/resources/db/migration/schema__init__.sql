CREATE TABLE category_group
(
    category_group_id BIGINT NOT NULL AUTO_INCREMENT,
    created_at        DATETIME(6),
    last_modified_at  DATETIME(6),
    name              VARCHAR(10),
    PRIMARY KEY (category_group_id)
) ENGINE = InnoDB;

CREATE TABLE concrete_category
(
    concrete_category_id         BIGINT NOT NULL AUTO_INCREMENT,
    created_at                   DATETIME(6),
    last_modified_at             DATETIME(6),
    name                         VARCHAR(10),
    sub_category_sub_category_id BIGINT NOT NULL,
    PRIMARY KEY (concrete_category_id)
) engine = InnoDB;

CREATE TABLE member
(
    member_id BIGINT NOT NULL,
    name      VARCHAR(10),
    deleted   VARCHAR(10),
    PRIMARY KEY (member_id)
) ENGINE = InnoDB;

CREATE TABLE post
(
    post_id              BIGINT NOT NULL AUTO_INCREMENT,
    category_group_id    BIGINT,
    coment_count         INTEGER,
    concrete_category_id BIGINT,
    content              VARCHAR(1000),
    created_at           DATETIME(6),
    deleted              VARCHAR(10),
    last_modified_at     DATETIME(6),
    member_id            BIGINT,
    sub_category_id      BIGINT,
    PRIMARY KEY (post_id)
) ENGINE = InnoDB;

CREATE TABLE post_image
(
    post_image_id    BIGINT NOT NULL AUTO_INCREMENT,
    created_at       DATETIME(6),
    image_url        VARCHAR(255),
    last_modified_at DATETIME(6),
    post_id          BIGINT NOT NULL,
    deleted          VARCHAR(10),
    PRIMARY KEY (post_image_id)
) ENGINE = InnoDB;

CREATE TABLE review
(
    review_id        BIGINT NOT NULL AUTO_INCREMENT,
    content          VARCHAR(1000),
    created_at       DATETIME(6),
    deleted          VARCHAR(10),
    last_modified_at DATETIME(6),
    member_id        BIGINT NOT NULL,
    post_id          BIGINT NOT NULL,
    PRIMARY KEY (review_id)
) engine = InnoDB;

CREATE TABLE review_image
(
    review_image_id  BIGINT NOT NULL AUTO_INCREMENT,
    created_at       DATETIME(6),
    image_url        VARCHAR(1000),
    last_modified_at DATETIME(6),
    post_id          BIGINT NOT NULL,
    review_id        BIGINT NOT NULL,
    deleted          VARCHAR(10),
    PRIMARY KEY (review_image_id)
) ENGINE = InnoDB;

CREATE TABLE sub_category
(
    sub_category_id   BIGINT NOT NULL AUTO_INCREMENT,
    created_at        DATETIME(6),
    last_modified_at  DATETIME(6),
    name              varchar(10),
    category_group_id BIGINT NOT NULL,
    PRIMARY KEY (sub_category_id)
) ENGINE = InnoDB;

ALTER TABLE concrete_category
    ADD CONSTRAINT fk_sub_category_id_concrete_category_id
        FOREIGN KEY (sub_category_sub_category_id)
            REFERENCES sub_category (sub_category_id);

ALTER TABLE review_image
    ADD CONSTRAINT fk_review_id_review_image_id
        FOREIGN KEY (review_id)
            REFERENCES review (review_id);

ALTER TABLE sub_category
    ADD CONSTRAINT fk_category_group_id_sub_category_id
        FOREIGN KEY (category_group_id)
            REFERENCES category_group (category_group_id)
