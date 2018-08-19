package me.yipzale.water.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import me.yipzale.water.article.util.DateJsonDeserializer;
import me.yipzale.water.article.util.DateJsonSerializer;

import java.util.Date;

@JsonIgnoreProperties({"handler"})
public class Article {
    private Integer id;

    private Integer archiveId;

    private String title;

    private String slug;

    private String keywords;

    @JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date createdAt;

    @JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date updatedAt;

    private String summary;

    @JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date date;

    private ArticleContent content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(ArticleContent content) {
        this.content = content;
    }

    @JsonIgnore
    public ArticleContent getContent() {
        return content;
    }

    @JsonProperty("content")
    public ArticleContent articleContent() {
        return content;
    }
}