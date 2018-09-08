package me.yipzale.water.article.mapper;

import java.util.List;

import me.yipzale.water.article.mybatis.sql.QueryBuilder;
import me.yipzale.water.article.entity.Article;
import org.apache.ibatis.annotations.*;

public interface ArticleMapper {

    @Insert({
        "insert into articles (id, archive_id, ",
        "title, slug, keywords, ",
        "created_at, updated_at, ",
        "summary, date)",
        "values (#{id,jdbcType=INTEGER}, #{archiveId,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{slug,jdbcType=VARCHAR}, #{keywords,jdbcType=VARCHAR}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{summary,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true)
    int insert(Article record);


    @Update({
            "update articles",
            "set archive_id = #{archiveId,jdbcType=INTEGER},",
            "title = #{title,jdbcType=VARCHAR},",
            "slug = #{slug,jdbcType=VARCHAR},",
            "keywords = #{keywords,jdbcType=VARCHAR},",
            "created_at = #{createdAt,jdbcType=TIMESTAMP},",
            "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
            "summary = #{summary,jdbcType=VARCHAR},",
            "date = #{date,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Article record);

    @Select({
        "select",
        "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
        "from articles",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("articleMap")
    Article selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
            "from articles",
            "where slug = #{slug,jdbcType=VARCHAR}"
    })
    @ResultMap("articleMap")
    Article selectBySlug(String slug);

    @SelectProvider(type = QueryProvider.class, method = "select")
    @ResultMap("articleMap")
    List<Article> select(QueryBuilder builder);

    @Select({
            "select",
            "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
            "from articles",
            "where archive_id = #{archiveId, jdbcType=INTEGER}"
    })
    @ResultMap("articleMap")
    List<Article> selectByArchiveId(Integer archiveId);

}