package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Flight;

@Repository
public class FlightDAO {
    public void insert(Flight flight, Session session){
        session.save(flight);
    }

}
