/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.dao;
import java.util.ArrayList;
import java.util.List;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;

/**
 *
 * @author Loek
 */
public interface UserDAO {
    
    Boolean createUser(User u);
    
    Boolean editUser(User u);
        
    void removeUser(User u);
    
    void setPassword(Long id , String password);
    
    int countFollowers(String username);

    int countFollowing(String username);
    
    User findUserByName(String username);
    
    User getFollower(Long id);
    
    User getFollows(Long id);
    
    List<User> findAllFollowers(Long id);
    
    List<User> findAllFollows(Long id);    
    
    List<User> findAllUsers();
}
