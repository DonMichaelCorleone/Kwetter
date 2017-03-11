package nl.loek.kwetter.dao;

import nl.loek.kwetter.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.dao.UserDAO;

@Stateless
//@JPA
//@LocalBean
public class UserDAOJPAImpl implements UserDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;


    public UserDAOJPAImpl() {
     
    }


    @Override
    public void createUser(User user) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            em.persist(user);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public void editUser(User user) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            em.merge(user);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public void removeUser(User u) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
//            User removeUser = em.find(User.class, u.getId());
            em.remove(u);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public User findUserByName(String username) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        return em.find(User.class, username);
    }

    @Override
    public List<User> findAllFollowers(Long id) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        Query q = em.createNamedQuery("User.findAllfollowers", User.class);
        q.setParameter("id", id);
        return (List<User>) q.getResultList();
    }

    @Override
    public List<User> findAllFollows(Long id) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        Query q = em.createNamedQuery("User.findAllFollows", User.class);
        q.setParameter("id", id);
        return (List<User>) q.getResultList();
    }

    @Override
    public User getFollower(Long id) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        Query q = em.createNamedQuery("User.getFollower", User.class);
        q.setParameter("id", id);
        return (User) q.getResultList();
    }

    @Override
    public User getFollows(Long id) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        Query q = em.createNamedQuery("User.getFollows", User.class);
        q.setParameter("id", id);
        return (User) q.getResultList();
    }

    @Override
    public void setPassword(Long id, String password) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
        Query q = em.createNamedQuery("User.setPassword", User.class);
        q.setParameter("id", id);
        q.setParameter("password", password);
    }

    @Override
    public List<User> findAllUsers() {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        Query q = em.createNamedQuery("User.findAll", User.class);
        return (List<User>)     em.createQuery(
    "SELECT u FROM user u ")
				.getResultList();
        
    }
    
    @Override
	public int countFollowers(String username) {
		Number count = (Number) em
				.createNativeQuery(
						"SELECT count(f.user) FROM Following f where f.follows = ?1")
				.setParameter(1, username).getSingleResult();
		return count.intValue();
	}

	@Override
	public int countFollowing(String username) {
		Number count = (Number) em
				.createNativeQuery(
						"SELECT count(f.follows) FROM Following f where f.user = ?1")
				.setParameter(1, username).getSingleResult();
		return count.intValue();
	}
}
