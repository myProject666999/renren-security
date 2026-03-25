--AI自动生成 股票合约表
CREATE TABLE gp_contract (
  id bigint NOT NULL COMMENT '主键ID',
  stock_id varchar(50) NOT NULL COMMENT '股票ID',
  stock_code varchar(20) NOT NULL COMMENT '股票代码',
  stock_name varchar(100) COMMENT '股票名称',
  exchange varchar(20) COMMENT '交易所',
  price decimal(10,2) COMMENT '股票价格',
  market varchar(20) COMMENT '市场类型',
  currency varchar(10) COMMENT '货币类型',
  status tinyint COMMENT '状态 0:禁用 1:启用',
  create_time datetime COMMENT '创建时间',
  update_time datetime COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE INDEX idx_stock_id (stock_id),
  INDEX idx_stock_code (stock_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票合约表';

-- 插入测试数据
INSERT INTO gp_contract (id, stock_id, stock_code, stock_name, exchange, price, market, currency, status, create_time, update_time) VALUES 
(1, 'SH600000', '600000', '浦发银行', 'SH', 12.56, '主板', 'CNY', 1, NOW(), NOW()),
(2, 'SH600001', '600001', '邯郸钢铁', 'SH', 8.32, '主板', 'CNY', 1, NOW(), NOW()),
(3, 'SH600002', '600002', '齐鲁石化', 'SH', 6.78, '主板', 'CNY', 1, NOW(), NOW()),
(4, 'SH600003', '600003', '东北高速', 'SH', 3.45, '主板', 'CNY', 1, NOW(), NOW()),
(5, 'SZ000001', '000001', '平安银行', 'SZ', 15.23, '主板', 'CNY', 1, NOW(), NOW()),
(6, 'SZ000002', '000002', '万科A', 'SZ', 28.56, '主板', 'CNY', 1, NOW(), NOW()),
(7, 'SZ000003', '000003', 'PT金田A', 'SZ', 2.34, '主板', 'CNY', 0, NOW(), NOW()),
(8, 'HK00001', '00001', '长江和记实业', 'HK', 56.78, '港股', 'HKD', 1, NOW(), NOW());
