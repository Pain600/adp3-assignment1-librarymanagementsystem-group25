package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.City;
import za.ac.cput.repository.CityIRepository;
import za.ac.cput.service.impl.CityService;

import java.util.Optional;

@Component
public class CityAPI {
    private final CityService cityService;

    private final CityIRepository cityIRepository;

    @Autowired
    public CityAPI(CityService cityService, CityIRepository cityIRepository){
        this.cityService = cityService;
        this.cityIRepository = cityIRepository;

    }

    public City create(City city){
        Optional<City> cityId = cityIRepository.findById(city.getId());
        Optional<City > newCity = Optional.ofNullable(cityIRepository.findCitiesById(city.getId()));

        if (newCity.isPresent()){
            throw new IllegalStateException("City ID already exists");
        }

        if (cityId.isPresent()){
            throw new IllegalStateException("ID already exists");
        }
        return this.cityIRepository.save(city);
    }

    public City read(City getCity){
        Optional<City> cityId = cityIRepository.findById(getCity.getId());


        if (cityId.isEmpty()){
            throw new IllegalStateException("City not found");
        }
        return this.cityService.read(getCity.getName() + getCity.getSuburb());
    }

    public City delete(City toDelete){
        Optional<City> cityId = cityIRepository.findById(toDelete.getId());


        if (cityId.isPresent()){
            this.cityService.delete(toDelete.getId());
        }else{
            throw new IllegalStateException("City not found");
        }
        return toDelete;

    }





}
