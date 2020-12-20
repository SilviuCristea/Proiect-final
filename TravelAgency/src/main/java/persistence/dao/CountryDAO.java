package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Country;

import javax.persistence.NoResultException;
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
    public void insert(Country country, Session session){
        session.save(country);
    }
    public Country findCountryByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findCountryByNameQuery = session.createNamedQuery("findCountryByName");
        findCountryByNameQuery.setParameter("name", name);
        Country country = null;
        try {
            country = (Country) findCountryByNameQuery.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return country;
    }
    public Country findCountryByName(String name, Session session){
        Query findCountryByNameQuery = session.createNamedQuery("findCountryByName");
        findCountryByNameQuery.setParameter("name", name);
        Country country = null;
        try {
            country = (Country) findCountryByNameQuery.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return country;
    }

    public List<Country> findCountryByContinent(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findCountryByContinentQuery = session.createNamedQuery("findCountryByContinent");
        findCountryByContinentQuery.setParameter("name", name);
        List<Country> countryList = findCountryByContinentQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return countryList;
    }
}
