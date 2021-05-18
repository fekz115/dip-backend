package org.fekz115.dip.repository;

import org.fekz115.dip.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {}
