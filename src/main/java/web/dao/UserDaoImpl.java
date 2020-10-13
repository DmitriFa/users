package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
   // private SessionFactory sessionFactory;

 //   @Autowired
 //  public void setSessionFactory(SessionFactory sessionFactory) {
  //    this.sessionFactory = sessionFactory;
  //}

   @PersistenceContext
   EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers(int page) {
      Session session = em.unwrap(Session.class);
      //  Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(User user) {
       Session session = em.unwrap(Session.class);
   // Session session = sessionFactory.getCurrentSession();
      session.persist(user);
    }

    @Override
    public void delete(User user){
      // Session session = sessionFactory.getCurrentSession();
      Session session = em.unwrap(Session.class);
       session.delete(user);

    }

    @Override
    public void edit(User user) {
      // Session session = sessionFactory.getCurrentSession();
      Session session = em.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User getById(int id) {
     // Session session = sessionFactory.getCurrentSession();
     Session session = em.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public int usersCount() {
     //  Session session = sessionFactory.getCurrentSession();
       Session session = em.unwrap(Session.class);
        return session.createQuery("select count(*) from User", Number.class).getSingleResult().intValue();
    }

    @Override
    public boolean checkTitle(String name) {
   //  Session session = sessionFactory.getCurrentSession();
     Session session = em.unwrap(Session.class);
        Query query;
        query = session.createQuery("from User where name = :name");
        query.setParameter("name", name);
        return query.list().isEmpty();
    }
}
