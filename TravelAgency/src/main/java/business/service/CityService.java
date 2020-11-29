package business.service;

import business.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.entities.City;
import persistence.entities.Country;

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
}
