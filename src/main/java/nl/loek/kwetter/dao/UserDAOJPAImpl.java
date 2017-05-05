package nl.loek.kwetter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import nl.loek.kwetter.model.User;

@Stateless
public class UserDAOJPAImpl implements UserDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public UserDAOJPAImpl() {

    }

    @Override
    public Boolean createUser(User user) {
        try {
            em.persist(user);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    @Override
    public Boolean editUser(User user) {
        try {
            em.merge(user);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    @Override
    public Boolean removeUser(String username) {
        try {
            em.remove(this.findUserByName(username));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findUserByName(String username) {
        Query q = em.createNamedQuery("User.findByName", User.class);
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    @Override
    public User getFollower(Long id) {
        Query q = em.createNamedQuery("User.getFollower", User.class);
        q.setParameter("id", id);
        return (User) q.getResultList();
    }

    @Override
    public User getFollows(Long id) {
        Query q = em.createNamedQuery("User.getFollows", User.class);
        q.setParameter("id", id);
        return (User) q.getResultList();
    }

    @Override
    public void setPassword(Long id, String password) {
        Query q = em.createNamedQuery("User.setPassword", User.class);
        q.setParameter("id", id);
        q.setParameter("password", password);
    }
    
    
//    @Override
//    public Boolean authenticateUser(String username, String password) {
//        Query q = em.createNamedQuery("User.setPassword", User.class);
//        q.setParameter("id", id);
//        q.setParameter("password", password);
//    }

    @Override
    public List<User> findAllUsers() {
        Query q = em.createNamedQuery("User.findAll", User.class);
        return (List<User>) q.getResultList();

    }

    @Override
    public int countFollowers(Long id) {
        Number count = (Number) em
                .createNativeQuery(
                        "SELECT count(f.friendId) FROM followers f where f.userId = ?id")
                .setParameter("id", id).getSingleResult();
        return count.intValue();
    }

    @Override
    public int countFollowing(Long id) {
        Number count = (Number) em
                .createNativeQuery(
                        "SELECT count(f.userId) FROM followers f where f.friendId = ?id")
                .setParameter("id", id).getSingleResult();
        return count.intValue();
    }
}
