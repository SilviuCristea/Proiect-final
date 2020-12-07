package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.City;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class CityDAO {
    public void insert(City city){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
    }

    public City findCityByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findCityByNameQuery = session.createNamedQuery("findCityByName");
        findCityByNameQuery.setParameter("name", name);
        City city = null;
        try {
            city = (City) findCityByNameQuery.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return city;
    }
}
