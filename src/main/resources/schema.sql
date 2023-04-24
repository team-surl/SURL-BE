CREATE TABLE IF NOT EXISTS `tbl_user`
(
    `id`         CHAR(36)     NOT NULL,
    `account_id` VARCHAR(100) NOT NULL,
    `username`   VARCHAR(255) NOT NULL,
    `password`   CHAR(60)     NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `version`    INT          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_id` (`account_id`),
    UNIQUE KEY `username` (`username`)
) CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tbl_url`
(
    `id`         CHAR(36)      NOT NULL,
    `url`        VARCHAR(1000) NOT NULL,
    `short_url`       CHAR(6)       NOT NULL,
    `created_at` TIMESTAMP     NOT NULL,
    `version`    INT           NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `url` (`url`)
) CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tbl_visitor`
(
    `id`         CHAR(36)     NOT NULL,
    `url_id`     CHAR(36)     NOT NULL,
    `ip`         VARCHAR(50)  NOT NULL,
    `country`    VARCHAR(100) NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `version`    INT          NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`url_id`) REFERENCES `tbl_url` (`id`)
) CHARSET=utf8;
