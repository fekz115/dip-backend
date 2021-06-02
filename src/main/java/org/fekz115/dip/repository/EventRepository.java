package org.fekz115.dip.repository;

import org.fekz115.dip.model.Event;
import org.fekz115.dip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByArticleAuthor(User author);
}
