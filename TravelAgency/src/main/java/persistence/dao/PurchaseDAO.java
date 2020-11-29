package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Purchase;

@Repository
public class PurchaseDAO {
    public void insert(Purchase purchase){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
        session.close();
    }
}
