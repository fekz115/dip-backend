package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Address;
import org.fekz115.dip.model.City;
import org.fekz115.dip.model.Country;
import org.fekz115.dip.model.Street;
import org.fekz115.dip.repository.*;

@AllArgsConstructor
public class AddressService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;
    private final AddressRepository addressRepository;
    private final LocationRepository locationRepository;

    public Address getAddress(Address address) {
        Street street;
        if(address.getStreet().getId() != 0) {
            street = streetRepository.findById(address.getStreet().getId()).orElseThrow();
        } else {
            street = streetRepository.findByName(address.getStreet().getName()).orElseGet(() -> {
                City city;
                if(address.getStreet().getCity().getId() != 0) {
                    city = cityRepository.findById(address.getStreet().getCity().getId()).orElseThrow();
                } else {
                    city = cityRepository.findByName(address.getStreet().getCity().getName()).orElseGet(() -> {
                        Country country;
                        if(address.getStreet().getCity().getCountry().getId() != 0) {
                            country = countryRepository.findById(address.getStreet().getCity().getCountry().getId()).orElseThrow();
                        } else {
                            country = countryRepository.findByName(address.getStreet().getCity().getCountry().getName()).orElse(countryRepository.save(address.getStreet().getCity().getCountry()));
                        }
                        City c = new City();
                        c.setName(address.getStreet().getCity().getName());
                        c.setCountry(country);
                        return cityRepository.save(c);
                    });
                }
                Street ans = new Street();
                ans.setCity(city);
                ans.setName(address.getStreet().getName());
                return streetRepository.save(ans);
            });
        }
        return addressRepository.findByBuildingAndStreet(address.getBuilding(), street).orElseGet(() -> {
            var location = locationRepository.save(address.getLocation());
            Address a = new Address();
            a.setBuilding(address.getBuilding());
            a.setStreet(street);
            a.setLocation(location);
            return addressRepository.save(a);
        });
    }

}
