package io.renren;

import io.renren.common.utils.Result;
import io.renren.controller.ApiGpContractController;
import io.renren.dao.GpContractDao;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GpContractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GpContractDao gpContractDao;

    @Autowired
    private ApiGpContractController apiGpContractController;

    private static final int TEST_CONTRACT_ID_1 = 600001;
    private static final int TEST_CONTRACT_ID_2 = 600002;
    private static final int TEST_CONTRACT_ID_3 = 600003;

    @Test
    @Order(1)
    public void testInsertTestData() {
        System.out.println("=== 插入测试数据 ===");
        
        String deleteSql = "DELETE FROM gp_contract WHERE contract_id IN (" + TEST_CONTRACT_ID_1 + ", " + TEST_CONTRACT_ID_2 + ", " + TEST_CONTRACT_ID_3 + ")";
        jdbcTemplate.update(deleteSql);
        
        String insertSql = "INSERT INTO gp_contract (id, contract_id, symbol, sec_type, currency, exchange, market_price, creator, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(insertSql, 
            System.currentTimeMillis(), TEST_CONTRACT_ID_1, "600001", "STK", "CNY", "SH", new BigDecimal("10.50"), 1L, new Date());
        
        jdbcTemplate.update(insertSql, 
            System.currentTimeMillis() + 1, TEST_CONTRACT_ID_2, "600002", "STK", "CNY", "SH", new BigDecimal("25.80"), 1L, new Date());
        
        jdbcTemplate.update(insertSql, 
            System.currentTimeMillis() + 2, TEST_CONTRACT_ID_3, "000001", "STK", "CNY", "SZ", new BigDecimal("15.20"), 1L, new Date());
        
        String countSql = "SELECT COUNT(*) FROM gp_contract WHERE contract_id IN (" + TEST_CONTRACT_ID_1 + ", " + TEST_CONTRACT_ID_2 + ", " + TEST_CONTRACT_ID_3 + ")";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);
        
        assertEquals(3, count, "应该插入3条测试数据");
        System.out.println("成功插入3条测试数据");
    }

    @Test
    @Order(2)
    public void testGetList() {
        System.out.println("=== 测试获取股票列表 ===");
        
        Result<List<GpContractListDTO>> result = apiGpContractController.list();
        
        assertNotNull(result, "返回结果不应为空");
        assertEquals(0, result.getCode(), "返回码应为0表示成功");
        assertNotNull(result.getData(), "数据不应为空");
        
        List<GpContractListDTO> list = result.getData();
        System.out.println("股票列表数量: " + list.size());
        
        for (GpContractListDTO dto : list) {
            System.out.println("股票信息: id=" + dto.getId() + 
                             ", contractId=" + dto.getContractId() + 
                             ", symbol=" + dto.getSymbol() + 
                             ", exchange=" + dto.getExchange() + 
                             ", marketPrice=" + dto.getMarketPrice());
            
            assertNotNull(dto.getId(), "id不应为空");
            assertNotNull(dto.getContractId(), "contractId不应为空");
            assertNotNull(dto.getSymbol(), "symbol不应为空");
            assertNotNull(dto.getExchange(), "exchange不应为空");
            assertNotNull(dto.getMarketPrice(), "marketPrice不应为空");
        }
        
        assertTrue(list.size() >= 3, "至少应有3条测试数据");
    }

    @Test
    @Order(3)
    public void testGetDetail() {
        System.out.println("=== 测试获取股票详情 ===");
        
        Result<GpContractEntity> result = apiGpContractController.detail(TEST_CONTRACT_ID_1);
        
        assertNotNull(result, "返回结果不应为空");
        assertEquals(0, result.getCode(), "返回码应为0表示成功");
        assertNotNull(result.getData(), "数据不应为空");
        
        GpContractEntity entity = result.getData();
        System.out.println("股票详情: " + entity);
        
        assertEquals(TEST_CONTRACT_ID_1, entity.getContractId(), "股票ID应匹配");
        assertEquals("600001", entity.getSymbol(), "股票代码应匹配");
        assertEquals("STK", entity.getSecType(), "证券类型应匹配");
        assertEquals("CNY", entity.getCurrency(), "货币类型应匹配");
        assertEquals("SH", entity.getExchange(), "交易所应匹配");
        assertNotNull(entity.getMarketPrice(), "股票价格不应为空");
        assertNotNull(entity.getId(), "id不应为空");
        assertNotNull(entity.getCreateDate(), "创建时间不应为空");
    }

    @Test
    @Order(4)
    public void testGetDetailNotFound() {
        System.out.println("=== 测试获取不存在的股票详情 ===");
        
        Result<GpContractEntity> result = apiGpContractController.detail(999999);
        
        assertNotNull(result, "返回结果不应为空");
        assertEquals(0, result.getCode(), "返回码应为0表示成功");
        assertNull(result.getData(), "不存在的股票应返回null");
        
        System.out.println("不存在的股票返回null，符合预期");
    }

    @Test
    @Order(5)
    public void testDaoGetList() {
        System.out.println("=== 测试DAO层获取列表 ===");
        
        List<GpContractListDTO> list = gpContractDao.getList();
        
        assertNotNull(list, "列表不应为空");
        assertTrue(list.size() >= 3, "至少应有3条测试数据");
        
        System.out.println("DAO层获取列表成功，数量: " + list.size());
    }

    @Test
    @Order(6)
    public void testDaoGetByContractId() {
        System.out.println("=== 测试DAO层获取详情 ===");
        
        GpContractEntity entity = gpContractDao.getByContractId(TEST_CONTRACT_ID_2);
        
        assertNotNull(entity, "实体不应为空");
        assertEquals(TEST_CONTRACT_ID_2, entity.getContractId(), "股票ID应匹配");
        assertEquals("600002", entity.getSymbol(), "股票代码应匹配");
        assertEquals("SH", entity.getExchange(), "交易所应匹配");
        
        System.out.println("DAO层获取详情成功: " + entity);
    }

    @Test
    @Order(7)
    public void testCleanup() {
        System.out.println("=== 清理测试数据 ===");
        
        String deleteSql = "DELETE FROM gp_contract WHERE contract_id IN (" + TEST_CONTRACT_ID_1 + ", " + TEST_CONTRACT_ID_2 + ", " + TEST_CONTRACT_ID_3 + ")";
        int rows = jdbcTemplate.update(deleteSql);
        
        System.out.println("已清理 " + rows + " 条测试数据");
    }
}
