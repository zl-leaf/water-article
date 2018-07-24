package me.yipzale.water.article.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"handler"})
public class Archive {
    public final static String SEPARATOR = "0x0";

    private Integer id;

    private String title;

    private String alias;

    private String path;

    private Integer parentId;

    private Date createdAt;

    private List<Archive> archiveNodes;

    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public List<Archive> getArchiveNodes() {
        return archiveNodes;
    }

    @JsonGetter("archiveNodes")
    public List<Archive> archiveNodes() {
        return archiveNodes;
    }

    public void setArchiveNodes(List<Archive> archiveNodes) {
        this.archiveNodes = archiveNodes;
    }

    @JsonIgnore
    public List<Article> getArticles() {
        return articles;
    }

    @JsonGetter("articles")
    public List<Article> articles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}