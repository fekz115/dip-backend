package org.fekz115.dip.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.fekz115.dip.model.Article;

public class ArticleServiceResponses {

    @Data
    @AllArgsConstructor
    public static class CreateArticleResponse {
        private final Article article;
    }
}
