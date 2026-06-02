CREATE TABLE user_model (
    merchant_id     VARCHAR(50)  NOT NULL,
    username        VARCHAR(50)  NOT NULL,
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    merchant_name   VARCHAR(100),
    password        VARCHAR(255) NOT NULL,
    mobile          VARCHAR(15),
    admin_id        VARCHAR(50),
    role            VARCHAR(20)  NOT NULL,
    message         VARCHAR(255),
    status          VARCHAR(20),
    token           VARCHAR(2000),

    CONSTRAINT pk_user_model PRIMARY KEY (merchant_id),
    CONSTRAINT uk_user_model_username UNIQUE (username),
--      CONSTRAINT chk_user_role
--           CHECK (role IN ('ROLE_ADMIN', 'ROLE_MERCHANT'))
);
