package me.yipzale.water.article.mapper;

import me.yipzale.water.article.mybatis.sql.QueryBuilder;

public class QueryProvider {
    public String select(QueryBuilder builder) {
        String sql = builder.getSql();
        return sql;
    }
}
