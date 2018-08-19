package me.yipzale.water.article.mapper;

import me.yipzale.water.article.mybatis.sql.QueryBuilder;

public class ArticleQuery extends QueryBuilder {

    public ArticleQuery() {
        select("articles.*");
        from("articles");
    }
}
