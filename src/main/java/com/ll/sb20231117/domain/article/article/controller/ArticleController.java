package com.ll.sb20231117.domain.article.article.controller;

import com.ll.sb20231117.domain.article.article.entity.Article;
import com.ll.sb20231117.domain.article.article.service.ArticleService;
import com.ll.sb20231117.global.reData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final List<Article> articles;

    @GetMapping("/article/write")
    String showWrite() {
        return "article/article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    RsData<Article> write(
            String title,
            String body
    ){
        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );

        String resultCode = rs.getResultCode();
        String msg = rs.getMsg();
        Article _article = rs.getData();

        return rs;
    }

    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle() {
        return articleService.findLastArticle();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articleService.findAll();
    }
}
