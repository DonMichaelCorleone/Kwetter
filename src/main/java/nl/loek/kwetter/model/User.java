/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.model;

import java.io.Serializable;
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;

/**
 *
 * @author Loek
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name= "User")
@NamedQueries({
    @NamedQuery(name = "User.setPassword", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.getFollows", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.getFollower", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.findAllFollows", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.findAllfollowers", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.findByUsername", query = "select u from User as u where u.userName= :username"),
    @NamedQuery(name = "User.findAll", query = "select u from User as u")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final long serialVersionUID = 1L;
    
    @Size(min = 1, max = 32)
    private String userName;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @Size(min = 1, max = 32)
    private List<Posting> tweets;
   
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @Size(min = 1, max = 32)
    private List<User> following;
    
    private String password;
    private String location;
    private String biography;
    private String websiteURL;
    private List<User> followers;
    
    public User(){
        
    }
    
    public User(String userName){
        this.userName = userName;
    }   

    public User(String userName, String profilePicture, ArrayList<Posting> tweets, ArrayList<User> followers, ArrayList<User> following, String location, String biography, String websiteURL) {
        this.userName = userName;
        this.profilePicture = profilePicture;
//        this.tweets = tweets;
//        this.followers = followers;
//        this.following = following;
        this.location = location;
        this.biography = biography;
        this.websiteURL = websiteURL;
    }
    private String profilePicture;
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

//    public ArrayList<Posting> getTweets() {
//        return tweets;
//    }
//
//    public void setTweets(ArrayList<Posting> tweets) {
//        this.tweets = tweets;
//    }
//
    public List<User> getFollowers() {
        return this.followers;
    }
//    
//    public String getPassword(){
//        return this.password;
//    }
//    
//    public void setPassword(String password){
//        this.password = password;
//    }
//
    public void addFollower(User follower) {
        this.followers.add(follower);
    }

//    public ArrayList<User> getFollowing() {
//        return following;
//    }
//
    public void addFollowing(User following) {
        this.following.add(following);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User[ id=" + id + " ]";
    }
}
