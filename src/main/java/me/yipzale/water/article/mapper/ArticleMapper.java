package me.yipzale.water.article.mapper;

import java.util.List;
import me.yipzale.water.article.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

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
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="archive_id", property="archiveId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="slug", property="slug", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP),
        @Result(
                column = "id",
                property = "content",
                one = @One(select = "me.yipzale.water.article.mapper.ArticleContentMapper.selectByArticleId", fetchType = FetchType.LAZY))
    })
    Article selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
            "from articles",
            "where slug = #{slug,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="archive_id", property="archiveId", jdbcType=JdbcType.INTEGER),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="slug", property="slug", jdbcType=JdbcType.VARCHAR),
            @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
            @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP)
    })
    Article selectBySlug(String slug);

    @Select({
        "select",
        "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
        "from articles",
        "order by ${sortby} ${order}",
        "limit #{page,jdbcType=INTEGER},#{limit,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="archive_id", property="archiveId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="slug", property="slug", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Article> selectAll(@Param("sortby") String sortby,@Param("order") String order,@Param("page") Integer page,@Param("limit") Integer limit);

    @Select({
            "select",
            "id, archive_id, title, slug, keywords, created_at, updated_at, summary, date",
            "from articles",
            "where archive_id = #{archiveId, jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="archive_id", property="archiveId", jdbcType=JdbcType.INTEGER),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="slug", property="slug", jdbcType=JdbcType.VARCHAR),
            @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
            @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Article> selectByArchiveId(Integer archiveId);

}