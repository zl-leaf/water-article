package me.yipzale.water.article.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginConfiguration {
    @Bean
    public DynamicPlugin dynamicPlugin() {
        return new DynamicPlugin();
    }
}
