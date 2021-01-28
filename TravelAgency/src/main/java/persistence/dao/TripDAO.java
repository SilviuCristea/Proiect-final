package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Trip;

import javax.persistence.Query;
import java.util.Date;
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

    public List<Trip> findUpcomingTrips(Date departureDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findUpcomingTripsQuery = session.createNamedQuery("findUpcomingTrips");
        findUpcomingTripsQuery.setParameter("departureDate", departureDate);
        List<Trip> tripList = findUpcomingTripsQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findUpcomingTripsByContinent(String name, Date departureDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByContinentQuery = session.createNamedQuery("findUpcomingTripsByContinent");
        findTripByContinentQuery.setParameter("name", name);
        findTripByContinentQuery.setParameter("departureDate", departureDate);
        List<Trip> tripList = findTripByContinentQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findUpcomingTripsByCountry(String name, Date departureDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByCountryQuery = session.createNamedQuery("findUpcomingTripsByCountry");
        findTripByCountryQuery.setParameter("name", name);
        findTripByCountryQuery.setParameter("departureDate", departureDate);
        List<Trip> tripList = findTripByCountryQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findUpcomingTripsByCity(String name, Date departureDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByCountryQuery = session.createNamedQuery("findUpcomingTripsByCity");
        findTripByCountryQuery.setParameter("name", name);
        findTripByCountryQuery.setParameter("departureDate", departureDate);
        List<Trip> tripList = findTripByCountryQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

    public List<Trip> findUpcomingTripsByHotel(String name, Date departureDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findTripByCountryQuery = session.createNamedQuery("findUpcomingTripsByHotel");
        findTripByCountryQuery.setParameter("name", name);
        findTripByCountryQuery.setParameter("departureDate", departureDate);
        List<Trip> tripList = findTripByCountryQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return tripList;
    }

}
