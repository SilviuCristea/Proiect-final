package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Hotel;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {
    public void insert(Hotel hotel){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(hotel);
        session.getTransaction().commit();
        session.close();
    }

    public List<Hotel> findHotelByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findHotelByNameQuery = session.createNamedQuery("findHotelByName");
        findHotelByNameQuery.setParameter("name", name);
        List<Hotel> hotelList = findHotelByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return  hotelList;
    }

    public List<String> findHotelByCity(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findHotelByCityQuery = session.createNamedQuery("findHotelByCity");
        findHotelByCityQuery.setParameter("name", name);
        List<String> hotelList =findHotelByCityQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return hotelList;
    }

}
