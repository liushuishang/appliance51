# CREATE TABLE IF NOT EXISTS  users (
#   username VARCHAR  (50) NOT NULL PRIMARY KEY,
#   `password` VARCHAR  (500) NOT NULL,
#   enabled BOOLEAN NOT NULL
# )  ;
#
# CREATE TABLE IF NOT EXISTS  authorities (
#   username VARCHAR  (50) NOT NULL,
#   authority VARCHAR  (50) NOT NULL,
#   CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
# ) ;
# CREATE UNIQUE INDEX   ix_auth_username ON authorities (username,authority);


-- 插入两个用户
insert users  VALUES('admin','admin123',1);
insert users  VALUES('guest','guest123',1);
-- 赋予guest权限ROLE_USER
insert authorities VALUES('admin','ROLE_ADMIN');
insert authorities VALUES('guest','ROLE_USER');

