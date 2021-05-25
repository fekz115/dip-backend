package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Article;
import org.fekz115.dip.model.Tag;
import org.fekz115.dip.repository.ArticleRepository;
import org.fekz115.dip.repository.TagRepository;

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
                                .tags(saveTags(request.getTags()))
                                .body(
                                        contentBodyService.createContentBody(
                                                new CreateContentBodyRequest(request.getContent())
                                        ).getContentBody()
                                )
                                .build()
                )
        );
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

}