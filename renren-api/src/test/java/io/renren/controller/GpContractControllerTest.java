/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import io.renren.ApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 股票合约控制器测试类
 *
 * @author Mark sunlightcs@gmail.com
 */
@SpringBootTest(classes = ApiApplication.class)
public class GpContractControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 测试股票列表接口
     */
    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.total").exists())
                .andExpect(jsonPath("$.data.list").exists());
    }

    /**
     * 测试股票列表接口 - 带查询条件
     */
    @Test
    public void testListWithParams() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .param("exchange", "HKEX")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").exists());
    }

    /**
     * 测试所有股票列表接口
     */
    @Test
    public void testAll() throws Exception {
        mockMvc.perform(get("/api/gpContract/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").isArray());
    }

    /**
     * 测试股票详情接口 - 不存在的记录
     */
    @Test
    public void testGetNotExist() throws Exception {
        mockMvc.perform(get("/api/gpContract/99999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    /**
     * 测试股票详情接口 - 格式错误
     */
    @Test
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get("/api/gpContract/abc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").exists());
    }

    /**
     * 测试列表接口缺少必要参数
     */
    @Test
    public void testListMissingParams() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").exists());
    }

    /**
     * 测试按股票代码查询
     */
    @Test
    public void testListBySymbol() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .param("symbol", "00700")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    /**
     * 测试按证券类型查询
     */
    @Test
    public void testListBySecType() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .param("secType", "STK")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    /**
     * 测试按货币类型查询
     */
    @Test
    public void testListByCurrency() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .param("currency", "HKD")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    /**
     * 测试组合查询条件
     */
    @Test
    public void testListWithMultipleParams() throws Exception {
        mockMvc.perform(get("/api/gpContract/list")
                .param("page", "1")
                .param("limit", "10")
                .param("exchange", "HKEX")
                .param("currency", "HKD")
                .param("secType", "STK")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }
}
