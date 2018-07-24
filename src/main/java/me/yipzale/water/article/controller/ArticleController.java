package me.yipzale.water.article.controller;

import me.yipzale.water.article.entity.Article;
import me.yipzale.water.article.entity.ArticleContent;
import me.yipzale.water.article.mapper.ArticleContentMapper;
import me.yipzale.water.article.mapper.ArticleMapper;
import me.yipzale.water.article.service.StoreArticleService;
import me.yipzale.water.article.vo.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private StoreArticleService storeArticleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse articles(
            @RequestParam(name = "sortby", defaultValue = "date") String sortby,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        List<Article> articleList = articleMapper.selectAll(sortby, order, page, limit);
        ApiResponse response = new ApiResponse(0, "success", articleList);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse getArticleById(
            @PathVariable(value = "id")Integer id,
            @RequestParam(name = "_with", defaultValue = "") String with
    ) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (!with.isEmpty()) {
            String[] withs = with.split(",");
            List<String> withList = Arrays.asList(withs);
            if (withList.contains("content")) {
                article.getContent();
            }
        }
        ApiResponse response = new ApiResponse(0, "success", article);
        return response;
    }

    @RequestMapping(value = "/@{slug}", method = RequestMethod.GET)
    public ApiResponse getArticleBySlug(@PathVariable(value = "slug")String slug) {
        Article article = articleMapper.selectBySlug(slug);
        ApiResponse response = new ApiResponse(0, "success", article);
        return response;
    }

    @RequestMapping(value = "/@{slug}/content", method = RequestMethod.GET)
    public ApiResponse getContentBySlug(@PathVariable(value = "slug")String slug) {
        Article article = articleMapper.selectBySlug(slug);
        ArticleContent articleContent = articleContentMapper.selectByArticleId(article.getId());
        ApiResponse response = new ApiResponse(0, "success", articleContent);
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResponse createArticle(@RequestBody ArticleModel model) {
        Article article = storeArticleService.storeArticle(model);
        model.setId(article.getId());
        ApiResponse response = new ApiResponse(0, "success", model);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ApiResponse updateArticle(@PathVariable(value = "id")Integer id, @RequestBody ArticleModel model) {
        model.setId(id);
        storeArticleService.storeArticle(model);
        ApiResponse response = new ApiResponse(0, "success", model);
        return response;
    }
}
