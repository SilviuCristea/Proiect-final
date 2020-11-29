package business.service;

import business.dto.ContinentDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CountryDAO;
import persistence.entities.Continent;
import persistence.entities.Country;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryDAO countryDAO;

    public void insert(CountryDTO countryDTO){
        Continent continent = new Continent();
        continent.setName(countryDTO.getContinentDTO().getName());
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setContinent(continent);
        countryDAO.insert(country);
    }

    public List<CountryDTO> findCountryByName(String name){
        List<Country> countryList = countryDAO.findCountryByName(name);
        List<CountryDTO> countryDTOList = new ArrayList<CountryDTO>();
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
