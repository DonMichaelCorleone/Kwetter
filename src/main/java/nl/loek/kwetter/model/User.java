/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.model;

import com.google.common.hash.Hashing;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    private String emailAdress;
    
    @Size(min = 1, max = 32)
    @Column(unique=true)
    private String userName;
    
    @Size(min = 1, max = 160)
    private String biography;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="followers",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="friendId")})
	private List<User> following;

	@ManyToMany(mappedBy="following")
	private List<User> followers;
        
    @Size(min = 8, max = 160)
    private String password;
    
    private String location;
    private String websiteURL;
    private String profilePicture;
    
    public User(){
        
    }
    
    public User(String userName, String password) {
        this.userName = userName;
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        this.role = Role.User;
    }
    
     public Role getUserRole() {
        return role;
    }

    public void setUserRole(Role userRole) {
        this.role = userRole;
    }
    
    
    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
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

    public List<User> getFollowers() {
        return this.followers;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
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
