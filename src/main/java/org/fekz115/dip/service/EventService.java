package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Event;
import org.fekz115.dip.model.EventLocation;
import org.fekz115.dip.repository.ArticleRepository;
import org.fekz115.dip.repository.EventLocationRepository;
import org.fekz115.dip.repository.EventRepository;
import org.fekz115.dip.repository.PictureRepository;
import org.fekz115.dip.service.request.EventServiceRequests.*;
import org.fekz115.dip.service.request.dto.EventLocationDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ArticleRepository articleRepository;
    private final EventLocationRepository eventLocationRepository;
    private final AddressService addressService;
    private final PictureRepository pictureRepository;

    public Event createEvent(
            CreateEventRequest request
    ) {
        return eventRepository
                .save(Event
                        .builder()
                        .article(articleRepository.findById(request.getArticleId()).orElseThrow())
                        .poster(pictureRepository.findById(request.getPictureId()).orElseThrow())
                        .locations(
                                saveEventLocations(request.getLocations())
                        )
                        .build()
                );
    }

    private List<EventLocation> saveEventLocations(List<EventLocationDto> locations) {
        return eventLocationRepository.saveAll(
                locations
                        .stream()
                        .map(x -> EventLocation
                                .builder()
                                .address(addressService.getAddress(x.getAddress()))
                                .startDate(x.getStartDate())
                                .finishDate(x.getFinishDate())
                                .build()
                        )
                        .collect(Collectors.toList())
        );
    }

    public void removeEvent(RemoveEventRequest request) {
        eventRepository.deleteById(request.getEventId());
    }

    public Event updateEvent(UpdateEventRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        request.getArticleId().ifPresent(article -> event.setArticle(articleRepository.findById(article).orElseThrow()));
        request.getLocations().ifPresent(l -> event.setLocations(saveEventLocations(l)));
        request.getPictureId().ifPresent(id -> event.setPoster(pictureRepository.findById(id).orElseThrow()));
        return eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

}
