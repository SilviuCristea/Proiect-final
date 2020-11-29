package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Airport;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AirportDAO {
    public void insert(Airport airport){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(airport);
        session.getTransaction().commit();
        session.close();
    }

    public List<Airport> findAirportByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findAirportByNameQuery = session.createNamedQuery("findAirportByName");
        findAirportByNameQuery.setParameter("name", name);
        List<Airport> airportsList = findAirportByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return airportsList;
    }
}
