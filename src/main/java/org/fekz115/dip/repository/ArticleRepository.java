package org.fekz115.dip.repository;

import org.fekz115.dip.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {}
