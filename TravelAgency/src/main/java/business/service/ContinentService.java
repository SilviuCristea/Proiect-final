package business.service;

import business.dto.ContinentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.entities.Continent;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContinentService {
    @Autowired
    ContinentDAO continentDAO;

    public void insert(ContinentDTO continentDTO){
        Continent continent = new Continent();
        continent.setName(continentDTO.getName());
        continentDAO.insert(continent);
    }

    public List<ContinentDTO> findContinentByName(String name){
        List<Continent> continentList = continentDAO.findContinentByName(name);
        List<ContinentDTO> continentDTOList = new ArrayList<ContinentDTO>();
        for (Continent continent : continentList){
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(continent.getName());
        }
        return continentDTOList;
    }
}
