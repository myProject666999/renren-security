/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import io.renren.ApiApplication;
import io.renren.common.utils.Result;
import io.renren.dto.ContractDetailDTO;
import io.renren.dto.ContractListDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * ContractController单元测试
 *
 * @author Mark sunlightcs@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ContractControllerTest {

    @Autowired
    private ContractController contractController;

    @Test
    public void testList() {
        Result<List<ContractListDTO>> result = contractController.list(null, null);
        assertNotNull("返回结果不应为null", result);
        assertTrue("响应应该成功", result.success());
        System.out.println("股票列表查询成功，记录数: " + (result.getData() != null ? result.getData().size() : 0));
        if (result.getData() != null) {
            for (ContractListDTO dto : result.getData()) {
                System.out.println("ID: " + dto.getId() + ", 股票ID: " + dto.getStockId() + ", 股票代码: " + dto.getStockCode() + ", 交易所: " + dto.getExchange() + ", 价格: " + dto.getPrice());
            }
        }
    }

    @Test
    public void testDetail() {
        // 先查询列表获取一个ID
        Result<List<ContractListDTO>> listResult = contractController.list(null, null);
        if (listResult.getData() != null && !listResult.getData().isEmpty()) {
            Long id = listResult.getData().get(0).getId();
            Result<ContractDetailDTO> result = contractController.detail(id);
            assertNotNull("返回结果不应为null", result);
            assertTrue("响应应该成功", result.success());
            assertNotNull("详情数据不应为null", result.getData());
            System.out.println("股票详情查询成功: " + result.getData().getStockName());
        } else {
            System.out.println("没有测试数据，请先执行SQL脚本插入数据");
        }
    }

    @Test
    public void testListWithFilter() {
        // 测试按交易所筛选
        Result<List<ContractListDTO>> result = contractController.list(null, "SH");
        assertNotNull("返回结果不应为null", result);
        assertTrue("响应应该成功", result.success());
        System.out.println("按交易所筛选查询成功，记录数: " + (result.getData() != null ? result.getData().size() : 0));
    }
}
