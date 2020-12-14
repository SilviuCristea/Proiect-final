package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Client;

import javax.persistence.Query;
import java.util.List;


@Repository
public class ClientDAO {
    public void insert(Client client){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }
    public List<Client> findClientByName(String firstName, String surname){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findClientByNameQuery = session.createNamedQuery("findClientByName");
        findClientByNameQuery.setParameter("firstName", firstName);
        findClientByNameQuery.setParameter("surname", surname);
        List<Client> clientList = findClientByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return clientList;
    }

    public List<String> findClientByUser(String userName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findClientByUserQuery = session.createNamedQuery("findClientByUser");
        findClientByUserQuery.setParameter("userName", userName);
        List<String> clientList = findClientByUserQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return clientList;
    }
}
