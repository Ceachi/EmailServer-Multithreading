package service;

import dao.UserDaoInterface;
import entity.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Id;
import util.HibernateUtility;

public class UserImpl implements UserDaoInterface {

    private Query query;
    private Session session;

    @Override
    public List findAll() {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        query = session.createQuery("from User");
        List<User> users = query.list();

        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public User findById(int id) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        query = session.createQuery("from User where userId=:b");
        query.setParameter("b", id);
        Object marksQuery2 = query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return (User) marksQuery2;
    }
    
    @Override
    public Object findByEmail(String email) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        query = session.createQuery("from User where email=:b");
        query.setParameter("b", email);
        Object marksQuery2 = query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return (User) marksQuery2;
    }

    

    public void update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUsers(List users) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.flush();

        users.forEach((user) -> session.save(users));

        session.getTransaction().commit();
        session.close();

    }

    public void insert(User user) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
