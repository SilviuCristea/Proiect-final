package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Trip;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TripDAO {
    public void insert(Trip trip, Session session){
        session.saveOrUpdate(trip);
    }

    public List<Trip> findPromotedTrips(boolean promoted){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findPromotedTripsQuery = session.createNamedQuery("findPromotedTrips");
        findPromotedTripsQuery.setParameter("promoted", promoted);
        List<Trip> tripList = findPromotedTripsQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findAllTrip(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findAllTripQuery = session.createNamedQuery("findAllTrip");
        List<Trip> tripList = findAllTripQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<String> findTripByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByNameQuery = session.createNamedQuery("findTripByName");
        findTripByNameQuery.setParameter("name", name);
        List<String> tripList = findTripByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    /*public List<Trip> findTripByContinent(String continentName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByContinentQuery = session.createQuery("findTripByContinent");
        findTripByContinentQuery.setParameter("name", continentName);
        List<Trip> tripList = findTripByContinentQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findTripByCountry(String countryName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByCountryQuery = session.createNamedQuery("findTripByCountry");
        findTripByCountryQuery.setParameter("name", countryName);
        List<Trip> tripList = findTripByCountryQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }*/
}
