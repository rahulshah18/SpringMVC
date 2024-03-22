package service;

import java.util.List;
import model.NewHibernateUtil;
import model.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DataService {

    public int getMaxId() {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session openSession = factory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        Criteria criteria = openSession.createCriteria(Student.class);
        criteria.setProjection(Projections.max("id"));
        Object result = criteria.uniqueResult();
        int maxID = 0;
        if (result != null) {
            maxID = (int) result;
        }
        beginTransaction.commit();
        openSession.close();
        return maxID;
    }

    public void saveRecord(Student u) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session openSession = sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.save(u);
        beginTransaction.commit();
        openSession.close();
    }

    public boolean validateCredentials(String loginId, String password) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("loginId", loginId));
        List<Student> userList = criteria.list();
        for(Student u : userList)
        {
            System.out.println(userList);
        }
        transaction.commit();
        session.close();
        if (userList.isEmpty()) {
            return false;
        }
        for (Student user : userList) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Student getUserById(int id) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Student user = (Student) session.get(Student.class, id);
            return user;
        } catch (HibernateException e)
        {
            System.out.println(e);
            return null;
        } 
        finally {
            session.close();
        }
    }

    public boolean isLoginIdExists(String loginId) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("loginId", loginId));
        boolean exists = !criteria.list().isEmpty();
        session.close();
        return exists;
    }

    public List<Student> getAllRecords() {
        List<Student> users = null;
        Transaction transaction = null;
        Session session = null;
        try {
            SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Student.class);
            users = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }
}
