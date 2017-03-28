/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.Role;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;

/**
 *
 * @author Loek
 */
@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController implements Serializable {

    public String userName;

    public boolean tweetview = true;

    FacesContext facesContext;

    @Inject
    KwetterService kwetterService;

    /**
     * Creates a new instance of homeController
     */
    public HomeController() {

    }

    public String getUsername() {
        this.facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext()
                .getRequestParameterMap();
        String username = params.get("username");
        if (username != null) {
            this.userName = username;
            return this.userName;
        } else {
            throw new NullPointerException("User does not exist!");
        }
    }
    



    public boolean getTweetview() {
        return tweetview;
    }

    public void setTweetview(boolean tweetview) {
        this.tweetview = tweetview;
    }

    public List<User> getAllUsers() {
        return kwetterService.findAllUsers();
    }

    public User getSelectedUser() {
        return kwetterService.findByUsername(this.getUsername());
    }

    public int getFollowingCount() {
        return kwetterService.countFollowing(this.getSelectedUser().getId());
    }

    public int getFollowersCount() {
        return kwetterService.countFollowers(this.getSelectedUser().getId());
    }

    public int countTweets() {
        return kwetterService.findTweetsByUser(this.getUsername()).size();
    }

    public List<Posting> getTweets() {

        List<Posting> allTweets = kwetterService.findTweetsByUser(this.getUsername());
        return allTweets;
    }

    public boolean isAuthenticated() {
        if (facesContext.getExternalContext().getRemoteUser() == this.userName) {
            return true;
        } else {
            return false;
        }
    }
}
