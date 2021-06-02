package org.fekz115.dip.repository;

import org.fekz115.dip.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Integer> {
    Optional<Street> findByName(String name);
}
