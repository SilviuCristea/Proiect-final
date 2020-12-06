package business.service;

import business.dto.ContinentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.entities.Continent;


@Service
public class ContinentService {
    @Autowired
    ContinentDAO continentDAO;

    public void insert(ContinentDTO continentDTO){
        Continent continent = new Continent();
        continent.setName(continentDTO.getName());
        continentDAO.insert(continent);
    }

    public ContinentDTO findContinentByName(String name){
        Continent continent = continentDAO.findContinentByName(name);
        ContinentDTO continentDTO = new ContinentDTO();
        if (continent == null){
            return null;
        }
        continentDTO.setName(continent.getName());
        return continentDTO;
    }
}
