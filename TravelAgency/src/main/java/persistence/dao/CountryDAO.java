package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Country;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDAO {
    public void insert(Country country){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
        session.close();
    }
    public List<Country> findCountryByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findCountryByNameQuery = session.createNamedQuery("findCountryByName");
        findCountryByNameQuery.setParameter("name", name);
        List<Country> countryList = findCountryByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return countryList;
    }
}