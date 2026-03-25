/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service;

import io.renren.ApiApplication;
import io.renren.common.page.PageData;
import io.renren.dto.GpContractDTO;
import io.renren.dto.GpContractListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 股票合约服务测试类
 *
 * @author Mark sunlightcs@gmail.com
 */
@SpringBootTest(classes = ApiApplication.class)
public class GpContractServiceTest {

    @Autowired
    private GpContractService gpContractService;

    /**
     * 测试分页查询列表
     */
    @Test
    public void testPageList() {
        Map<String, Object> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "10");

        PageData<GpContractListDTO> pageData = gpContractService.pageList(params);

        assertNotNull(pageData);
        assertTrue(pageData.getTotal() >= 0);
        assertNotNull(pageData.getList());
    }

    /**
     * 测试查询所有列表
     */
    @Test
    public void testListAll() {
        Map<String, Object> params = new HashMap<>();
        List<GpContractListDTO> list = gpContractService.listAll(params);

        assertNotNull(list);
    }

    /**
     * 测试按条件查询
     */
    @Test
    public void testListByExchange() {
        Map<String, Object> params = new HashMap<>();
        params.put("exchange", "HKEX");
        params.put("page", "1");
        params.put("limit", "10");

        PageData<GpContractListDTO> pageData = gpContractService.pageList(params);

        assertNotNull(pageData);
        assertNotNull(pageData.getList());
    }

    /**
     * 测试按股票代码查询
     */
    @Test
    public void testListBySymbol() {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", "00700");
        params.put("page", "1");
        params.put("limit", "10");

        PageData<GpContractListDTO> pageData = gpContractService.pageList(params);

        assertNotNull(pageData);
    }

    /**
     * 测试获取详情 - 存在的记录
     */
    @Test
    public void testGetExist() {
        // 假设数据库中有contract_id=1的记录
        GpContractDTO dto = gpContractService.get(1L);
        // 如果存在则验证字段
        if (dto != null) {
            assertNotNull(dto.getContractId());
            assertNotNull(dto.getSymbol());
        }
    }

    /**
     * 测试获取详情 - 不存在的记录
     */
    @Test
    public void testGetNotExist() {
        GpContractDTO dto = gpContractService.get(99999L);
        assertNull(dto);
    }

    /**
     * 测试列表DTO字段映射
     */
    @Test
    public void testListDtoFields() {
        Map<String, Object> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "10");

        PageData<GpContractListDTO> pageData = gpContractService.pageList(params);

        if (pageData.getList() != null && !pageData.getList().isEmpty()) {
            GpContractListDTO dto = pageData.getList().get(0);
            // 验证列表接口返回的字段
            assertNotNull(dto.getId());
            assertNotNull(dto.getContractId());
            assertNotNull(dto.getSymbol());
            assertNotNull(dto.getExchange());
            assertNotNull(dto.getMarketPrice());
        }
    }

    /**
     * 测试详情DTO字段映射
     */
    @Test
    public void testDetailDtoFields() {
        // 先查询列表获取一个存在的contractId
        Map<String, Object> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "1");
        PageData<GpContractListDTO> pageData = gpContractService.pageList(params);

        if (pageData.getList() != null && !pageData.getList().isEmpty()) {
            Integer contractId = pageData.getList().get(0).getContractId();
            GpContractDTO dto = gpContractService.get(contractId.longValue());

            assertNotNull(dto);
            // 验证详情接口返回的所有字段
            assertNotNull(dto.getId());
            assertNotNull(dto.getContractId());
            assertNotNull(dto.getSymbol());
            assertNotNull(dto.getSecType());
            assertNotNull(dto.getCurrency());
            assertNotNull(dto.getExchange());
            assertNotNull(dto.getMarketPrice());
            assertNotNull(dto.getCreator());
            assertNotNull(dto.getCreateDate());
        }
    }
}
