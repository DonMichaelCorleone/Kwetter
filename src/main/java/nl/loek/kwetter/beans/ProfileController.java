/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;

/**
 *
 * @author Loek
 */
@ManagedBean(name = "profileController")
@ViewScoped
public class ProfileController implements Serializable {

    public String userName;

    public String searchString;

    public List<Posting> Tweets;

    FacesContext facesContext;

    @Inject
    KwetterService kwetterService;

    /**
     * Creates a new instance of homeController
     */
    public ProfileController() {
        this.Tweets = new ArrayList<Posting>();
    }

    public String getUsername() {
        this.facesContext = FacesContext.getCurrentInstance();
        this.userName = facesContext.getExternalContext().getRemoteUser();
        return this.userName;
    }

    public List<User> getAllUsers() {
        return kwetterService.findAllUsers();
    }

    public List<Posting> getAllPostings() {
        List<Posting> tweets = new ArrayList<Posting>();
        for (User follower : this.getSelectedUser().getFollowing()) {
            List<Posting> userPostings = kwetterService.findTweetsByUser(follower.getUserName());
            for (Posting posting : userPostings) {
                tweets.add(posting);
            }
        };
        return tweets;
    }

    public void searchPostings(String search) {
        this.Tweets.clear();
        List<Posting> allPostings = kwetterService.findAllPostings();
        for (Posting p : allPostings) {
            if (p.getContent().toLowerCase().contains(search.toLowerCase())) {
                Tweets.add(p);
            }
        }
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public User getSelectedUser() {
        return kwetterService.findByUsername(this.getUsername());
    }

    public User editUser() {
        this.facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext()
                .getRequestParameterMap();
        String username = params.get("username");
        String biography = params.get("biography");
        String location = params.get("location");

        User selectedUser = this.getSelectedUser();
            selectedUser.setUserName(username);
            selectedUser.setBiography(biography);
            selectedUser.setLocation(location);
            this.kwetterService.editUser(selectedUser);
            return selectedUser;
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
        return this.Tweets;
    }

    public void addPosting() {
        this.facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext()
                .getRequestParameterMap();
        String content = params.get("content");
        String title = params.get("title");
        User u = this.getSelectedUser();
        Posting p = new Posting(u.getUserName(), title, content);
        this.kwetterService.createPosting(p);
        this.Tweets.add(p);

    }

    public void logout() throws IOException {
        this.facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        this.facesContext.getExternalContext().redirect("http://localhost:8080/Kwetter/");
    }
}
