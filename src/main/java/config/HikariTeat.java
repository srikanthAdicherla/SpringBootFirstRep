package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/*
 * @author KK JavaTutorials
 * Utility class which is responsible to get JDBC connection object using
 * Hikari DataSource connection pool With MYSQL Database.
 */
public class HikariTeat {
    public static DataSource getDatasource() {
        DataSource datasource = null;
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/sri");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        System.out.println(config.getJdbcUrl());

        datasource = new HikariDataSource(config);
        if(datasource!=null){
            return datasource;
        }else {
            return null;
        }

    }
}



