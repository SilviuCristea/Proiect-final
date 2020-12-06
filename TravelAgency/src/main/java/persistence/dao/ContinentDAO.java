package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Continent;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class ContinentDAO {
    public void insert(Continent continent){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
        session.close();
    }

    public Continent findContinentByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findContinentByNameQuery = session.createNamedQuery("findContinentByName");
        findContinentByNameQuery.setParameter("name", name);
        Continent continent = null;
        try {
            continent = (Continent) findContinentByNameQuery.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return continent;
    }
}
