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
//    @JPA
    private UserDAO userDAO;

    @Inject
//    @JPA
    private PostingDAO postingDAO;

    public KwetterService() {
    }

    public User findByUsername(String username) {
        User user = userDAO.findUserByName(username);
        return user;
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public int countFollowers(String username) {
        return userDAO.countFollowers(username);
    }

    public int countFollowing(String username) {
        return userDAO.countFollowing(username);
    }

    public List<Posting> findTweetsByUser(String username) {
        return postingDAO.findByUser(username);
    }

    public int countTweets(String username) {
        return postingDAO.findByUser(username).size();
    }
}
