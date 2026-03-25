package io.renren;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class GpContractApiTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetAllContracts() {
        String sql = "SELECT * FROM gp_contract LIMIT 10";
        List<Map<String, Object>> contracts = jdbcTemplate.queryForList(sql);
        
        System.out.println("=== gp_contract 表数据 ===");
        System.out.println("记录数: " + contracts.size());
        for (Map<String, Object> contract : contracts) {
            System.out.println(contract);
        }
    }
}
