INSERT INTO `appliance51`.`service_charge` (
  `id`,
  `order`,
  `price`,
  `specification`,
  `service_item_id`
)
VALUES
  (
   UUID(),
    0,
    98,
    '单门',
    (SELECT id FROM service_item WHERE NAME='冰箱')
  ) ;
INSERT INTO `appliance51`.`service_charge` (
  `id`,
  `order`,
  `price`,
  `specification`,
  `service_item_id`
)
VALUES
  (
   UUID(),
    0,
    198,
    '双门',
    (SELECT id FROM service_item WHERE NAME='冰箱')
  ) ;
  INSERT INTO `appliance51`.`service_charge` (
  `id`,
  `order`,
  `price`,
  `specification`,
  `service_item_id`
)
VALUES
  (
   UUID(),
    0,
    298,
    '三门',
    (SELECT id FROM service_item WHERE NAME='冰箱')
  ) ;


