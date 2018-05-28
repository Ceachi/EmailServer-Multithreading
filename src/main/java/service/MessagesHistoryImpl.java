/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MessagesDaoInterface;
import entity.MessagesHistory;
import entity.User;
import java.util.List;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtility;

/**
 *
 * @author CeachiBogdan
 */
public class MessagesHistoryImpl implements MessagesDaoInterface<MessagesHistory> {

    private Query query;
    private Session session;

    @Override
    public List<MessagesHistory> findAll() {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        query = session.createQuery("from MessagesHistory");
        List<MessagesHistory> messages = query.list();

        session.getTransaction().commit();
        session.close();;
        return messages;
    }

    @Override
    public MessagesHistory findById(Id id) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        query = session.createQuery("from MessagesHistory where messageId=:b");
        query.setParameter("b", id);
        Object marksQuery2 = query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return (MessagesHistory) marksQuery2;

    }

    @Override
    public void update(MessagesHistory entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertMessages(List<MessagesHistory> messages) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.flush();

        messages.forEach((message) -> session.save(message));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insert(MessagesHistory message) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("");
        session.save( message);

        session.getTransaction().commit();
        session.close();
    }

}
