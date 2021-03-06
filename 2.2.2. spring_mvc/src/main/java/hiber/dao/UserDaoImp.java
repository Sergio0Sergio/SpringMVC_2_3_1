package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

  @PersistenceContext
  private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public List<User> listUsers() {
        Query query = em.createQuery("SELECT user FROM User user");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @Override
    public User getUser(long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public void updateUser(User user) {
        User u = em.find(User.class, user.getId());
        em.detach(u);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        em.merge(u);
    }
}

