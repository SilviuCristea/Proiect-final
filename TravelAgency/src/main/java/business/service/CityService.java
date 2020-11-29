package business.service;

import business.dto.CityDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.entities.City;
import persistence.entities.Country;
import persistence.entities.Purchase;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityDAO cityDAO;

    public void insert(CityDTO cityDTO){
        Country country = new Country();
        country.setName(cityDTO.getCountryDTO().getName());
        City city = new City();
        city.setName(cityDTO.getName());
        city.setCountry(country);
        cityDAO.insert(city);
    }
    public List<CityDTO> findCityByName(String name){
        List<City> cityList = cityDAO.findCityByName(name);
        List<CityDTO> cityDTOList = new ArrayList<CityDTO>();
        for (City city : cityList){
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(city.getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(city.getCountry().getName());
            cityDTO.setCountryDTO(countryDTO);
            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }
}
