package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Room;

@Repository
public class RoomDAO {
    public void insert(Room room){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
        session.close();
    }
}