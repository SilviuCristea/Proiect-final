package business.service;

import business.dto.CityDTO;
import business.dto.ContinentDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.City;
import persistence.entities.Continent;
import persistence.entities.Country;

@Service
public class CityService {
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insert(CityDTO cityDTO){
        City city = new City();
        Country foundCountry = countryDAO.findCountryByName(cityDTO.getCountryDTO().getName());
        if (foundCountry == null){
            Country country = new Country();
            Continent foundContinent = continentDAO.findContinentByName(cityDTO.getCountryDTO().getContinentDTO().getName());
            if (foundContinent == null){
                Continent continent = new Continent();
                continent.setName(cityDTO.getCountryDTO().getContinentDTO().getName());
                country.setContinent(continent);
            }else{
                country.setContinent(foundContinent);
            }
            country.setName(cityDTO.getCountryDTO().getName());
            city.setCountry(country);
        }else{
            city.setCountry(foundCountry);
        }
        city.setName(cityDTO.getName());
        cityDAO.insert(city);
    }

    public CityDTO findCityByName(String name){
        City city = cityDAO.findCityByName(name);
        if (city == null){
            return null;
        }
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(city.getName());
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(city.getCountry().getName());
        cityDTO.setCountryDTO(countryDTO);
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(city.getCountry().getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);
        return cityDTO;
    }

}
