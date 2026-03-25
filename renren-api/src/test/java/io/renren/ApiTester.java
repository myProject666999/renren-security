/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren;

import io.renren.dao.ContractDao;
import io.renren.entity.ContractEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API测试工具类
 *
 * 使用说明：
 * 1. 先执行 renren-api/db/gp_contract.sql 脚本创建表和插入测试数据
 * 2. 确保MySQL服务在 localhost:3306 运行，用户名root，密码123456
 * 3. 运行此测试类验证功能
 *
 * @author Mark sunlightcs@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class, ApiTester.TestConfig.class})
public class ApiTester {

    @Configuration
    @MapperScan("io.renren.dao")
    public static class TestConfig {
    }

    @Autowired
    private ContractDao contractDao;

    @Test
    public void testQueryList() {
        System.out.println("=== 测试查询股票列表 ===");
        Map<String, Object> params = new HashMap<>();
        List<ContractEntity> list = contractDao.getSimpleList(params);
        System.out.println("查询结果数量: " + list.size());
        for (ContractEntity entity : list) {
            System.out.printf("ID: %d, 股票ID: %s, 股票代码: %s, 交易所: %s, 价格: %.2f%n",
                    entity.getId(), entity.getStockId(), entity.getStockCode(),
                    entity.getExchange(), entity.getPrice());
        }
        System.out.println("=== 测试完成 ===");
    }

    @Test
    public void testQueryDetail() {
        System.out.println("=== 测试查询股票详情 ===");
        // 先查询一条数据
        Map<String, Object> params = new HashMap<>();
        List<ContractEntity> list = contractDao.getSimpleList(params);
        if (!list.isEmpty()) {
            Long id = list.get(0).getId();
            ContractEntity detail = contractDao.selectById(id);
            if (detail != null) {
                System.out.println("找到详情数据:");
                System.out.printf("ID: %d%n", detail.getId());
                System.out.printf("股票ID: %s%n", detail.getStockId());
                System.out.printf("股票代码: %s%n", detail.getStockCode());
                System.out.printf("股票名称: %s%n", detail.getStockName());
                System.out.printf("交易所: %s%n", detail.getExchange());
                System.out.printf("价格: %.2f%n", detail.getPrice());
                System.out.printf("市场类型: %s%n", detail.getMarket());
                System.out.printf("货币类型: %s%n", detail.getCurrency());
                System.out.printf("状态: %d%n", detail.getStatus());
                System.out.printf("创建时间: %s%n", detail.getCreateTime());
            } else {
                System.out.println("未找到详情数据，ID: " + id);
            }
        } else {
            System.out.println("没有测试数据，请先执行SQL脚本");
        }
        System.out.println("=== 测试完成 ===");
    }

    @Test
    public void testQueryWithFilter() {
        System.out.println("=== 测试带筛选的查询 ===");
        Map<String, Object> params = new HashMap<>();
        params.put("exchange", "SH");
        List<ContractEntity> list = contractDao.getSimpleList(params);
        System.out.println("上海交易所股票数量: " + list.size());
        for (ContractEntity entity : list) {
            System.out.printf("股票代码: %s, 名称: %s%n", entity.getStockCode(), entity.getExchange());
        }
        System.out.println("=== 测试完成 ===");
    }
}
