package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Continent;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContinentDAO {
    public void insert(Continent continent){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
        session.close();
    }

    public List<Continent> findContinentByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findContinentByNameQuery = session.createNamedQuery("findContinentByName");
        findContinentByNameQuery.setParameter("name", name);
        List<Continent> continentList = findContinentByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return continentList;
    }
}
