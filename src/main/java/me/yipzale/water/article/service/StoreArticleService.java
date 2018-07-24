package me.yipzale.water.article.service;

import me.yipzale.water.article.entity.Archive;
import me.yipzale.water.article.entity.Article;
import me.yipzale.water.article.entity.ArticleContent;
import me.yipzale.water.article.mapper.ArchiveMapper;
import me.yipzale.water.article.mapper.ArticleContentMapper;
import me.yipzale.water.article.mapper.ArticleMapper;
import me.yipzale.water.article.vo.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StoreArticleService {
    @Autowired
    private ParseArchiveService parseArchiveService;

    @Autowired
    private ArchiveMapper archiveMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    public Article storeArticle(ArticleModel model) {
        // archive entity
        List<Archive> archiveList = parseArchiveService.date2Archives(model.getDate());
        int archiveId = 0;
        for (Archive archive : archiveList) {
            archive.setParentId(archiveId);
            if (archive.getId() == null) {
                archiveId = archiveMapper.insert(archive);
            } else {
                archiveId = archive.getId();
            }
        }

        // article entity
        Article article;
        if (model.getId() != null) {
            article = articleMapper.selectByPrimaryKey(model.getId());
        } else {
            article = new Article();
            article.setCreatedAt(new Date());
            if (model.getDate() != null) {
                article.setDate(model.getDate());
            } else {
                article.setDate(new Date());
            }
        }
        article.setArchiveId(archiveId);
        article.setTitle(model.getTitle());
        article.setSlug(model.getSlug());
        article.setKeywords(model.getKeywords());
        article.setSummary(model.getSummary());
        article.setUpdatedAt(new Date());

        if (model.getId() == null) {
            articleMapper.insert(article);
        } else {
            articleMapper.updateByPrimaryKey(article);
        }

        // article content
        ArticleContent articleContent;
        if (model.getId() != null) {
            articleContent = articleContentMapper.selectByArticleId(model.getId());
        } else {
            articleContent = new ArticleContent();
            articleContent.setArticleId(article.getId());
        }
        articleContent.setContent(model.getContent().getContent());
        if (model.getId() == null) {
            articleContentMapper.insert(articleContent);
        } else {
            articleContentMapper.updateByArticleId(articleContent);
        }

        return article;
    }

}
