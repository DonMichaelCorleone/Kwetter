package nl.loek.kwetter.dao;

import nl.loek.kwetter.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import nl.loek.kwetter.dao.UserDAO;

@Stateless
public class UserDAOJPAImpl implements UserDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public UserDAOJPAImpl() {
     
    }

    @Override
    public Boolean createUser(User user) {
        try{
            em.persist(user);
            return true;
        }
        catch(NotFoundException e){
            return false;
        }
    }

    @Override
    public Boolean editUser(User user) {
        try{
            em.merge(user);
            return true;
        }
        catch(NotFoundException e){
            return false;
        }
    }

    @Override
    public void removeUser(User u) {
            em.remove(u);
    }

    @Override
    public User findUserByName(String username) {
        Query q = em.createNamedQuery("User.findByName", User.class);
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    @Override
    public List<User> findAllFollowers(Long id) {
        Query q = em.createNamedQuery("User.findAllfollowers", User.class);
        q.setParameter("id", id);
        return (List<User>) q.getResultList();
    }

    @Override
    public List<User> findAllFollows(Long id) {
        Query q = em.createNamedQuery("User.findAllFollows", User.class);
        q.setParameter("id", id);
        return (List<User>) q.getResultList();
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

    @Override
    public List<User> findAllUsers() {
          Query q = em.createNamedQuery("User.findAll", User.class);
		return (List<User>) q.getResultList();
        
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
