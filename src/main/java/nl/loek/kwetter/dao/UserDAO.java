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
    
    void createUser(User u);
    
    void editUser(User u);
        
    void removeUser(User u);
    
    User findUserByName(String username);
    
    List<User> findAllFollowers(Long id);
    
    List<User> findAllFollows(Long id);    
    
    User getFollower(Long id);
    
    User getFollows(Long id);
    
    void setPassword(Long id , String password);
    
    List<User> findAllUsers();
    
    int countFollowers(String username);

    int countFollowing(String username);
}
