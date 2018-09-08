package me.yipzale.water.article.mapper;

import java.util.List;

import me.yipzale.water.article.entity.Archive;
import me.yipzale.water.article.mybatis.sql.QueryBuilder;
import org.apache.ibatis.annotations.*;

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
    @ResultMap("archiveMap")
    Archive selectByPath(String path);

    @Select({
        "select",
        "id, title, alias, path, parent_id, created_at",
        "from archives",
        "where parent_id = #{parentId,jdbcType=INTEGER}",
        "order by ${sortby} ${order}"
    })
    @ResultMap("archiveMap")
    List<Archive> selectAll(@Param("parentId") Integer parentId, @Param("sortby") String sortby, @Param("order") String order);

    @Select({
            "select",
            "id, title, alias, path, parent_id, created_at",
            "from archives",
            "where parent_id = #{parentId,jdbcType=INTEGER}"
    })
    @ResultMap("archiveMap")
    List<Archive> selectByParentId(Integer parentId);

    @Select({
            "select",
            "archives.id, archives.title, archives.alias, archives.path, archives.parent_id, archives.created_at,archiveNodes.id as cid",
            "from archives",
            "left join archives as archiveNodes on archiveNodes.parent_id=archives.id",
            "where archives.parent_id = #{parentId,jdbcType=INTEGER}",
            "order by ${sortby} ${order}"
    })
    @ResultMap("archiveMap")
    List<Archive> selectAllWithOpenData(@Param("parentId") Integer parentId, @Param("sortby") String sortby, @Param("order") String order);


    @SelectProvider(type = QueryProvider.class, method = "select")
    @ResultMap("archiveMap")
    List<Archive> select(QueryBuilder builder);
}