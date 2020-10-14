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
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers(int page) {
        return em.createQuery("from User").setFirstResult(10 * (page - 1)).setMaxResults(10).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(getById(user.getId()));
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public User getById(int id) {
        return (User) em.find(User.class, id);
    }

    @Override
    public int usersCount() {
        return em.createQuery("select count(*) from User", Number.class).getSingleResult().intValue();
    }

    @Override
    public boolean checkTitle(String lastName) {
        Query query;
        query = (Query) em.createQuery("from User where lastName = :lastName");
        query.setParameter("lastName", lastName);
        return query.list().isEmpty();
    }
}
