package business.service;

import business.dto.ContinentDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.Continent;
import persistence.entities.Country;

import java.util.ArrayList;
import java.util.List;


@Service
public class CountryService {
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insert(CountryDTO countryDTO){
        Country country = new Country();
        Continent foundContinent = continentDAO.findContinentByName(countryDTO.getContinentDTO().getName());
        if (foundContinent == null){
            Continent continent = new Continent();
            continent.setName(countryDTO.getContinentDTO().getName());
            country.setContinent(continent);
        }else{
            country.setContinent(foundContinent);
        }
        country.setName(countryDTO.getName());
        countryDAO.insert(country);
    }

    public CountryDTO findCountryByName(String name){
        Country country = countryDAO.findCountryByName(name);
        if (country == null){
            return null;
        }
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(country.getName());
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(country.getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);
        return countryDTO;
    }

    public List<CountryDTO> findCountryByContinent(String name){
        List<CountryDTO> countryDTOList = new ArrayList<>();
        List<Country> countryList = countryDAO.findCountryByContinent(name);
        for (Country country : countryList){
           CountryDTO countryDTO = new CountryDTO();
           countryDTO.setName(country.getName());
           ContinentDTO continentDTO = new ContinentDTO();
           continentDTO.setName(country.getContinent().getName());
           countryDTO.setContinentDTO(continentDTO);
           countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }
}
