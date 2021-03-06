package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Article;
import org.fekz115.dip.model.Tag;
import org.fekz115.dip.repository.ArticleRepository;
import org.fekz115.dip.repository.TagRepository;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fekz115.dip.service.request.ContentBodyServiceRequests.*;
import static org.fekz115.dip.service.request.ArticleServiceRequests.*;
import static org.fekz115.dip.service.response.ArticleServiceResponses.*;

@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final ContentBodyService contentBodyService;

    public CreateArticleResponse createArticle(CreateArticleRequest request) {
        return new CreateArticleResponse(
                articleRepository.save(
                        Article
                                .builder()
                                .title(request.getTitle())
                                .author(request.getAuthor())
                                .publicationDate(request.getPublicationDate())
                                .showAuthor(request.isShowAuthor())
                                .canBeRated(request.isCanBeRated())
                                .canBeCommented(request.isCanBeCommented())
                                .tags(request.getTags()  != null ? saveTags(request.getTags()) : new HashSet<>())
                                .body(
                                        contentBodyService.createContentBody(
                                                new CreateContentBodyRequest(request.getContent())
                                        ).getContentBody()
                                )
                                .comments(new LinkedList<>())
                                .likes(new HashSet<>())
                                .dislikes(new HashSet<>())
                                .build()
                ),
                request.getAuthor()
        );
    }

    public UpdateArticleResponse updateArticle(UpdateArticleRequest request) {
        var article = findById(request.getId());
        request.getTitle().ifPresent(article::setTitle);
        request.getCanBeCommented().ifPresent(article::setCanBeCommented);
        request.getCanBeRated().ifPresent(article::setCanBeRated);
        request.getContent().ifPresent(body -> article.setBody(contentBodyService.createContentBody(new CreateContentBodyRequest(body)).getContentBody()));
        request.getShowAuthor().ifPresent(article::setShowAuthor);
        request.getPublicationDate().ifPresent(article::setPublicationDate);
        request.getTags().ifPresent(tags -> article.setTags(saveTags(tags)));
        return new UpdateArticleResponse(articleRepository.save(article), request.getUser());
    }

    public ChangeArticleRatingResponse changeArticleRating(ChangeArticleRatingRequest request) {
        var article = findById(request.getArticleId());
        article.getLikes().remove(request.getUser());
        article.getDislikes().remove(request.getUser());
        switch (request.getNewRatingState()) {
            case LIKED:
                article.getLikes().add(request.getUser());
                break;
            case DISLIKED:
                article.getDislikes().add(request.getUser());
                break;
            case UNRATED:
                break;
        }
        return new ChangeArticleRatingResponse(articleRepository.save(article), request.getUser());
    }

    public FindArticleByIdResponse findArticleById(FindArticleByIdRequest request) {
        return new FindArticleByIdResponse(
                findById(request.getArticleId()),
                request.getUser()
        );
    }

    public FindArticlesResponse findArticles(FindArticlesRequest request) {
        PageRequest page = PageRequest.of(
                request.getPage().getPage(),
                request.getPage().getPageSize()
        );
        return new FindArticlesResponse(
                articleRepository.findAll(page),
                request.getUser()
        );
    }

    public RemoveArticleResponse removeArticle(RemoveArticleRequest request) {
        articleRepository.deleteById(request.getArticleId());
        return new RemoveArticleResponse();
    }

    private Set<Tag> saveTags(Set<Tag> tags) {
        return tags
                .stream()
                .map(tag -> {
                    if(tag.getId() != 0) {
                        return tagRepository.findById(tag.getId()).orElseThrow();
                    } else {
                        return tagRepository.save(tag);
                    }
                })
                .collect(Collectors.toSet());
    }

    private Article findById(int id) {
        return articleRepository.findById(id).orElseThrow();
    }
}
