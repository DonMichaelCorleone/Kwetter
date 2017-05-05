package nl.loek.kwetter.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import nl.loek.kwetter.dao.PostingDAO;
import nl.loek.kwetter.dao.UserDAO;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;

@Stateless  
public class KwetterService {
    
    @Inject
    private UserDAO userDAO;

    @Inject
    private PostingDAO postingDAO;

    public KwetterService() {
    }

    public User findByUsername(String username) {
        return userDAO.findUserByName(username);
    }

    public Boolean createUser(User user) {
        return userDAO.createUser(user);
    }

    public Boolean editUser(User user) {
        return userDAO.editUser(user);
    }
    
    public Boolean removeUser(String username) {
        return userDAO.removeUser(username);
    }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }
    
     public int countFollowers(Long id) {
        return userDAO.countFollowers(id);
    }

    public int countFollowing(Long id) {
        return userDAO.countFollowing(id);
    }

    public List<Posting> findTweetsByUser(String u) {
        return postingDAO.findByUser(u);
    }
    
    public List<Posting>findAllPostings() {
        return postingDAO.findAll();
    }
    
    public Posting findPosting(Long id) {
        return postingDAO.findPosting(id);
    }
    
    public Boolean editPosting(Posting posting) {
        return postingDAO.editPosting(posting);
    }
    
    public Boolean removePosting(Long id) {
        return postingDAO.removePosting(id);
    }
    
    public Boolean createPosting(Posting p) {
        return postingDAO.createPosting(p);
    }
    
//    public Boolean authenticateUser(String username, String password){
//        return userDAO.authenticateUser(username, password); 
//    }

}
