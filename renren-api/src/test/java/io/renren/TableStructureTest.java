package io.renren;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TableStructureTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetTableStructure() {
        String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'renren_security' AND TABLE_NAME = 'gp_contract'";
        List<Map<String, Object>> columns = jdbcTemplate.queryForList(sql);
        
        System.out.println("=== gp_contract 表结构 ===");
        for (Map<String, Object> column : columns) {
            System.out.println("字段名: " + column.get("COLUMN_NAME") + 
                             ", 类型: " + column.get("DATA_TYPE") + 
                             ", 注释: " + column.get("COLUMN_COMMENT"));
        }
    }
}
