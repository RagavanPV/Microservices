package com.ragavan.controller;

import com.ragavan.data.ArticleDAO;
import com.ragavan.models.Article;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class ArticleController {
  @Autowired private ArticleDAO articleDAO;

  @GetMapping(value = "all")
  public List<Article> getAllArticles() {
    return articleDAO.getAllArticles();
  }
}
