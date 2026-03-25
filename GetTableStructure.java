import java.sql.*;

public class GetTableStructure {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/renren_security?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // 获取gp_contract表的列信息
            ResultSet columns = metaData.getColumns(null, null, "gp_contract", null);

            System.out.println("表 gp_contract 的结构:");
            System.out.println("=====================================");
            System.out.printf("%-20s %-15s %-10s %-10s%n", "字段名", "类型", "长度", "可空");
            System.out.println("-------------------------------------");

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                int nullable = columns.getInt("NULLABLE");

                System.out.printf("%-20s %-15s %-10d %-10s%n",
                    columnName,
                    dataType,
                    columnSize,
                    nullable == 1 ? "YES" : "NO"
                );
            }

            // 获取主键信息
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, "gp_contract");
            System.out.println("\n主键:");
            while (primaryKeys.next()) {
                System.out.println("  " + primaryKeys.getString("COLUMN_NAME"));
            }

        } catch (SQLException e) {
            System.err.println("错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
