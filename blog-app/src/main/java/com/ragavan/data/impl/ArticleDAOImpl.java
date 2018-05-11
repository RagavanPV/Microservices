package com.ragavan.data.impl;

import com.ragavan.data.ArticleDAO;
import com.ragavan.data.exception.DataAccessException;
import com.ragavan.models.Article;
import com.ragavan.utils.data.DataRetrieverUtilImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
  @Autowired DataRetrieverUtilImpl dataRetriever;

  @Override
  public List<Article> getAllArticles() {
    final String query = "from Article a";
    try {
      return dataRetriever.retrieveByHQL(query);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
