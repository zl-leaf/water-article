package me.yipzale.water.article.mapper;

import java.util.List;
import me.yipzale.water.article.entity.Archive;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

public interface ArchiveMapper {
    @Insert({
        "insert into archives (id, title, ",
        "alias, path, parent_id, ",
        "created_at)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{alias,jdbcType=VARCHAR}, #{path,jdbcType=VARCHAR}, #{parentId,jdbcType=INTEGER}, ",
        "#{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(Archive record);

    @Select({
        "select",
        "id, title, alias, path, parent_id, created_at",
        "from archives",
        "where path = #{path,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    Archive selectByPath(String path);

    @Select({
        "select",
        "id, title, alias, path, parent_id, created_at",
        "from archives",
        "order by #{sortby} #{order}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column = "id", property = "articles", many = @Many(select = "me.yipzale.water.article.mapper.ArticleMapper.selectByArchiveId", fetchType = FetchType.LAZY))
    })
    List<Archive> selectAll(@Param("sortby") String sortby, @Param("order") String order);

    @Select({
            "select",
            "id, title, alias, path, parent_id, created_at",
            "from archives",
            "where parent_id = #{parentId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column = "id", property = "articles", many = @Many(select = "me.yipzale.water.article.mapper.ArticleMapper.selectByArchiveId", fetchType = FetchType.LAZY))
    })
    List<Archive> selectByParentId(Integer parentId);

    @Select({
            "select",
            "id, title, alias, path, parent_id, created_at",
            "from archives",
            "order by #{sortby} #{order}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="alias", property="alias", jdbcType=JdbcType.VARCHAR),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column = "id", property = "archiveNodes", many = @Many(select = "selectByParentId", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "articles", many = @Many(select = "me.yipzale.water.article.mapper.ArticleMapper.selectByArchiveId", fetchType = FetchType.LAZY))
    })
    List<Archive> selectAllWithOpenData(@Param("sortby") String sortby, @Param("order") String order);

}