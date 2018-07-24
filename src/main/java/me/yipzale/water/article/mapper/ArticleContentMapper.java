package me.yipzale.water.article.mapper;

import java.util.List;
import me.yipzale.water.article.entity.ArticleContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ArticleContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_contents
     *
     * @mbg.generated Sat Jul 21 17:07:42 CST 2018
     */
    @Insert({
        "insert into article_contents (id, article_id, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(ArticleContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_contents
     *
     * @mbg.generated Sat Jul 21 17:07:42 CST 2018
     */
    @Select({
        "select",
        "id, article_id, content",
        "from article_contents",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    ArticleContent selectByArticleId(Integer articleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_contents
     *
     * @mbg.generated Sat Jul 21 17:07:42 CST 2018
     */
    @Update({
        "update article_contents",
        "set article_id = #{articleId,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    int updateByArticleId(ArticleContent record);
}