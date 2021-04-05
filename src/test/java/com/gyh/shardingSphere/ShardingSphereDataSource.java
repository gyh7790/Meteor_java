//package com.gyh.shardingSphere;
//
//import ch.qos.logback.core.db.dialect.DBUtil;
//import com.gyh.modules.test.entity.TestApp;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
//import org.apache.shardingsphere.infra.hint.HintManager;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
//import org.springframework.core.io.buffer.DataBufferUtils;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author gyh
// * @Date 2021/3/27 21:10
// */
//public class ShardingSphereDataSource {
//    public static void main(String[] args) throws SQLException {
//
//        // 配置数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        HikariDataSource dataSource1 = new HikariDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/meteor?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("love1289");
//        dataSourceMap.put("ds0", dataSource1);
//
//        // 配置第二个数据源
//        // 配置第一个数据源
//        HikariDataSource dataSource2 = new HikariDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/meteor_1?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("love1289");
//        dataSourceMap.put("ds1", dataSource2);
//
//        // 配置 t_order 表规则
//        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order","ds${0..1}.t_order${0..1}");
//
//        // 配置分库策略
//        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id", "dbShardingAlgorithm"));
//
//        // 配置分表策略
//        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
//
//        // 省略配置 t_order_item 表规则...
//        // ...
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTables().add(orderTableRuleConfig);
//
//        // 配置分库算法
//        Properties dbShardingAlgorithmrProps = new Properties();
//        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));
//
//        // 配置分表算法
//        Properties tableShardingAlgorithmrProps = new Properties();
//        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));
//
//        // 创建 ShardingSphereDataSource
//        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
//
//        String sql = "SELECT t.* FROM test_app t WHERE t.id = ?";
//        for (int i = 10; i < 50; i++) {
//            try (HintManager hintManager = HintManager.getInstance();
//                 Connection conn = dataSource.getConnection();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//
//                    hintManager.addDatabaseShardingValue("id",i);
//                    hintManager.addDatabaseShardingValue("id",i);
//
//                    ps.setString(1, "" + (i / 10));
//                    try (ResultSet rs = ps.executeQuery()) {
//                        while(rs.next()) {
//                            TestApp ta = new TestApp();
//                            ta.setId(rs.getString(1));
//                            ta.setName(rs.getString(2));
//                            ta.setAge(rs.getInt(3));
//                            ta.setCreateDate(rs.getDate(4));
//                            ta.setDel(rs.getInt(5));
//                            System.out.println("====>" + ta.toString());
//                        }
//                    }
//            }
//        }
//    }
//
//}
