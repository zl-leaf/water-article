package me.yipzale.water.article.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceProxyConfiguration {

    @Autowired
    private DataSourceConfiguration configuration;

    @Bean
    @Primary
    public AbstractRoutingDataSource dataSouceProxy(){
        ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        Map<Object,Object> targetDataResources = new HashMap<Object, Object>();
        proxy.setDefaultTargetDataSource(configuration.getMaster());//默认源
        targetDataResources.put(DbType.MASTER,configuration.getMaster());
        if (configuration.getSlave() != null) {
            for (int i = 0; i < configuration.getSlave().size(); i++) {
                targetDataResources.put(DbType.SLAVE + String.valueOf(i),configuration.getSlave().get(i));
            }
            proxy.setSlaveCount(configuration.getSlave().size());
        }
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }
}
