package me.yipzale.water.article.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
@ConfigurationProperties("datasource")
public class DataSourceConfiguration {
    private DruidDataSource master;
    private List<DruidDataSource> slave;

    public void setMaster(DruidDataSource master) {
        this.master = master;
    }

    public DruidDataSource getMaster() {
        return master;
    }

    public void setSlave(List<DruidDataSource> slave) {
        this.slave = slave;
    }

    public List<DruidDataSource> getSlave() {
        return slave;
    }
}
