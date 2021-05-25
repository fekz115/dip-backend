package org.fekz115.dip.service.response;

import lombok.Data;
import org.fekz115.dip.model.Article;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.response.dto.ArticleDto;

public class ArticleServiceResponses {

    @Data
    public static class CreateArticleResponse {
        private final ArticleDto article;

        public CreateArticleResponse(Article article, User user) {
            this.article = new ArticleDto(article, user);
        }
    }

    @Data
    public static class ChangeArticleRatingResponse {
        private final ArticleDto article;

        public ChangeArticleRatingResponse(Article article, User user) {
            this.article = new ArticleDto(article, user);
        }
    }

    @Data
    public static class FindArticleByIdResponse {
        private final ArticleDto article;

        public FindArticleByIdResponse(Article article, User user) {
            this.article = new ArticleDto(article, user);
        }
    }
}
