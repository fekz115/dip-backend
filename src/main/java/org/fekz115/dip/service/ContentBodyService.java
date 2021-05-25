package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.ContentBody;
import org.fekz115.dip.model.ContentContainer;
import org.fekz115.dip.model.Text;
import org.fekz115.dip.repository.*;
import org.fekz115.dip.service.request.dto.ContentDto;


import java.util.LinkedList;
import java.util.List;

import static org.fekz115.dip.service.request.ContentBodyServiceRequests.*;
import static org.fekz115.dip.service.response.ContentBodyResponses.*;

@AllArgsConstructor
public class ContentBodyService {

    private final ContentBodyRepository contentBodyRepository;
    private final TextRepository textRepository;
    private final MusicRepository musicRepository;
    private final VideoRepository videoRepository;
    private final PictureRepository pictureRepository;
    private final ContentContainerRepository contentContainerRepository;

    CreateContentBodyResponse createContentBody(CreateContentBodyRequest request) {
        return new CreateContentBodyResponse(
                contentBodyRepository.save(
                        ContentBody
                                .builder()
                                .content(contentDtoToContentContainerList(request.getContent()))
                                .build()
                )
        );
    }

    private List<ContentContainer> contentDtoToContentContainerList(List<ContentDto> content) {
        var list = new LinkedList<ContentContainer>();
        int i = 0;
        for (ContentDto contentDto : content) {
            list.add(contentContainerRepository.save(contentDtoToContentContainer(i++, contentDto)));
        }
        return list;
    }

    private ContentContainer contentDtoToContentContainer(int index, ContentDto contentDto) {
        ContentContainer contentContainer = new ContentContainer();
        contentContainer.setIndex(index);
        if (contentDto instanceof ContentDto.TextDto) {
            var c = (ContentDto.TextDto) contentDto;
            contentContainer.setText(
                    textRepository.save(
                            Text
                                    .builder()
                                    .bold(c.isBold())
                                    .color(c.getColor())
                                    .data(c.getText())
                                    .italic(c.isItalic())
                                    .link(c.getLink())
                                    .separate(c.isSeparate())
                                    .size(c.getSize())
                                    .strike(c.isStrike())
                                    .build()
                    )
            );
        } else if (contentDto instanceof ContentDto.MediaDto) {
            var c = (ContentDto.MediaDto) contentDto;
            switch (c.getType()) {
                case IMAGE:
                    contentContainer.setPicture(pictureRepository.findById(c.getId()).orElseThrow());
                    break;
                case VIDEO:
                    contentContainer.setVideo(videoRepository.findById(c.getId()).orElseThrow());
                    break;
                case MUSIC:
                    contentContainer.setMusic(musicRepository.findById(c.getId()).orElseThrow());
                    break;
            }
        }
        return contentContainer;
    }

}
