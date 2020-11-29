package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.City;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CityDAO {
    public void insert(City city){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
    }

    public List<City> findCityByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findCityByNameQuery = session.createNamedQuery("findCityByName");
        findCityByNameQuery.setParameter("name", name);
        List<City> cityList = findCityByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return cityList;
    }
}
