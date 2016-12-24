
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
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c3',
    '',
    '家居保养1'
    ,'defaultImage'
  );

INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c3',
    '',
    '家居保养2'
    ,'defaultImage'
  );




INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c2',
    '',
    '家居维修1'
    ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c2',
    '',
    '家居维修2'
    ,'defaultImage'
  );



INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c1',
    '',
    '家电保养1'
    ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c1',
    '',
    '家电保养2'
    ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c1',
    '',
    '家电保养3'
    ,'defaultImage'
  );



INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '空调'
    ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '冰箱'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '热水器'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '电视'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '洗衣机'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '油烟机'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '灶台'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '微波炉'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '电磁炉'
        ,'defaultImage'
  );
INSERT INTO `service_item` (
  `id`,
  `category_id`,
  `description`,
  `name`,
  `icon`
)
VALUES
  (
    UUID(),
    '0a3445da-8d5d-11e6-840e-9c5c8e07b6c0',
    '',
    '中央空调'
        ,'defaultImage'
  );






