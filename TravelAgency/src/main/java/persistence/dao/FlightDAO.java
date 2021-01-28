package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Flight;


import javax.persistence.Query;
import java.util.List;

@Repository
public class FlightDAO {

    public void insert(Flight flight, Session session){
        session.save(flight);
    }

}
