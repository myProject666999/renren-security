-- 股票合约表
CREATE TABLE IF NOT EXISTS gp_contract (
  id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  stock_id varchar(50) NOT NULL COMMENT '股票ID',
  stock_code varchar(20) NOT NULL COMMENT '股票代码',
  stock_name varchar(100) COMMENT '股票名称',
  exchange varchar(20) COMMENT '交易所',
  stock_price decimal(10,2) COMMENT '股票价格',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE INDEX (stock_id),
  INDEX (stock_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票合约';

-- 插入测试数据
INSERT INTO gp_contract (id, stock_id, stock_code, stock_name, exchange, stock_price, create_date, update_date) VALUES
(1, '600000', '600000', '浦发银行', 'SSE', 8.50, NOW(), NOW()),
(2, '600001', '600001', '邯郸钢铁', 'SSE', 5.20, NOW(), NOW()),
(3, '000001', '000001', '平安银行', 'SZSE', 12.30, NOW(), NOW()),
(4, '000002', '000002', '万科A', 'SZSE', 15.60, NOW(), NOW()),
(5, '00700', '00700', '腾讯控股', 'HKEX', 380.00, NOW(), NOW()),
(6, '09988', '09988', '阿里巴巴-SW', 'HKEX', 85.50, NOW(), NOW()),
(7, 'BABA', 'BABA', '阿里巴巴', 'NYSE', 95.20, NOW(), NOW()),
(8, 'AAPL', 'AAPL', '苹果公司', 'NASDAQ', 185.30, NOW(), NOW());
