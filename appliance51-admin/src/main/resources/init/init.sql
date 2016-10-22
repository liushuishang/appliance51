CREATE TABLE IF NOT EXISTS  users (
  username VARCHAR  (50) NOT NULL PRIMARY KEY,
  `password` VARCHAR  (500) NOT NULL,
  enabled BOOLEAN NOT NULL
)  ;

CREATE TABLE IF NOT EXISTS  authorities (
  username VARCHAR  (50) NOT NULL,
  authority VARCHAR  (50) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
) ;
CREATE UNIQUE INDEX   ix_auth_username ON authorities (username,authority);


-- 插入两个用户
insert users  VALUES('admin','admin123',1);
insert users  VALUES('guest','guest123',1);
-- 赋予guest权限ROLE_USER
insert authorities VALUES('admin','ROLE_ADMIN');
insert authorities VALUES('guest','ROLE_USER');




-- 插入服务目录
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '为出现故障的家庭电器提供维修服务',
    '家电维修'
  );

INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c1',
    '为出现故障的家庭电器提供保养服务',
    '家电保养'
  );
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c2',
    '为出现故障的家具提供维修服务',
    '家居维修'
  );
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c3',
    '为出现故障的家具提供保养服务',
    '家居保养'
  );

-- 添加家电维修服务
-- 插入服务目录
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '为出现故障的家庭电器提供维修服务',
    '家电维修'
  );

INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c1',
    '为出现故障的家庭电器提供保养服务',
    '家电保养'
  );
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c2',
    '为出现故障的家具提供维修服务',
    '家居维修'
  );
INSERT INTO `service_category` (
  `id`,
  `description`,
  `name`
)
VALUES
  (
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c3',
    '为出现故障的家具提供保养服务',
    '家居保养'
  );


INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '空调'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '冰箱'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '热水器'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '电视'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '洗衣机'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '油烟机'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '灶台'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '微波炉'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '电磁炉'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '中央空调'
  );






