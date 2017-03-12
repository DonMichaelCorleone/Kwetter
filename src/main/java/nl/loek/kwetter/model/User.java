/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Loek
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="User")
@NamedQueries({
//    @NamedQuery(name = "User.setPassword", query = "select u from User as u where u.id= :id"),
//    @NamedQuery(name = "User.getFollows", query = "select u from User as u where u.id= :id"),
//    @NamedQuery(name = "User.getFollower", query = "select u from User as u where u.id= :id"),
//    @NamedQuery(name = "User.findAllFollows", query = "select u from User as u where u.id= :id"),
//    @NamedQuery(name = "User.findAllfollowers", query = "select u from User as u where u.id= :id"),
    @NamedQuery(name = "User.findByName", query = "select u from User as u where u.userName= :username"),
    @NamedQuery(name = "User.findAll", query = "select u from User as u")
})
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final long serialVersionUID = 1L;
    
    @Size(min = 1, max = 32)
    @Column(unique=true)
    private String userName;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Posting> tweets;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> following;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> followers;
    
    private String password;
    private String location;
    
    @Size(min = 1, max = 160)
    private String biography;
    
    private String websiteURL;
    private String profilePicture;
    
    public User(){
        
    }
    
    public User(String userName){
        this.userName = userName;
    }   

    public User(String userName, String password ,String location, String biography, String websiteURL) {
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.biography = biography;
        this.websiteURL = websiteURL;
    }
    
    
    
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

    public List<Posting> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Posting> tweets) {
        this.tweets = tweets;
    }

    public List<User> getFollowers() {
        return this.followers;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public void addFollower(User follower) {
        this.followers.add(follower);
    }

    public List<User> getFollowing() {
        return following;
    }

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
