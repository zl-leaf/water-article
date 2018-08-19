package me.yipzale.water.article.database;

import me.yipzale.water.article.mybatis.sql.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryProvider {
    Logger logger = LoggerFactory.getLogger(QueryProvider.class);

    public String select(QueryBuilder builder) {
        String sql = builder.getSql();
        logger.info(sql);
        return sql;
    }
}
