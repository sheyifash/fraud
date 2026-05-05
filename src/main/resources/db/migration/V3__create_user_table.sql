CREATE TABLE UserModel (
                           userId INT IDENTITY(1,1) PRIMARY KEY,
                           merchantId VARCHAR(50),
                           merchantName VARCHAR(50),
                           firstName VARCHAR(50),
                           lastName VARCHAR(50),
                           passwordHash VARCHAR(255) NOT NULL,
                           mobile VARCHAR(15),
                           adminId VARCHAR(15),
                           role VARCHAR(20) NOT NULL,

                           CONSTRAINT chk_user_role
                               CHECK (role IN ('ADMIN', 'USER'))
);