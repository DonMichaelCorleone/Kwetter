/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.dto;

/**
 *
 * @author Loek
 */
public class UserDTO {
    
    private String role;
    private String biography;
    private String websiteURL;
    private String location;
    private String userName;
    private String emailAddress;
    
    
    public UserDTO(String name, String emailAddress) {
        this.userName = name;
        this.emailAddress = emailAddress;
    }

    public UserDTO() {

    }

    public String getUserRole() {
        return role;
    }

    public void setUserRole(String userRole) {
        this.role = userRole;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String bio) {
        this.biography = bio;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String website) {
        this.websiteURL = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
