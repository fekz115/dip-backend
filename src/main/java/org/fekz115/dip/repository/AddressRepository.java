package org.fekz115.dip.repository;

import org.fekz115.dip.model.Address;
import org.fekz115.dip.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByBuildingAndStreet(String building, Street street);
}
