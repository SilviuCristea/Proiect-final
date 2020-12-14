package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Airport;

import javax.persistence.NoResultException;
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

    public Airport findAirportByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findAirportByNameQuery = session.createNamedQuery("findAirportByName");
        findAirportByNameQuery.setParameter("name", name);
        Airport airport = null;
        try {
            airport = (Airport) findAirportByNameQuery.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return airport;
    }

    public List<String> findAirportByCity(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findAirportByCityQuery = session.createNamedQuery("findAirportByCity");
        findAirportByCityQuery.setParameter("name", name);
        List<String> airportList = findAirportByCityQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return airportList;
    }
}
