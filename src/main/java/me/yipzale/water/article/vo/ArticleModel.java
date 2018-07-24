package me.yipzale.water.article.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import me.yipzale.water.article.util.DateJsonDeserializer;
import me.yipzale.water.article.util.DateJsonSerializer;

import java.util.Date;

public class ArticleModel {
    private Integer id;
    @JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date date;
    private String title;
    private String slug;
    private String summary;
    private String keywords;
    private ArticleContentModel content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public ArticleContentModel getContent() {
        return content;
    }

    public void setContent(ArticleContentModel content) {
        this.content = content;
    }
}
