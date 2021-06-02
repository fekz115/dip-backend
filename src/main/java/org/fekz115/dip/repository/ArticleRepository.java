package org.fekz115.dip.repository;

import org.fekz115.dip.model.Article;
import org.fekz115.dip.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByAuthor(User author);
}
