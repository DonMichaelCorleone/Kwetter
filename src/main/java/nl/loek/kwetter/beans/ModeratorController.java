/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

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
@ManagedBean(name = "moderatorController")
@ViewScoped
public class ModeratorController {

    @Inject
    private KwetterService kwetterService;
    
    private FacesContext facesContext;

    /**
     * Creates a new instance of ModeratorController
     */
    public ModeratorController() {
    }

    public List<Posting> getAllPostings() {
        List<Posting> allPostings = kwetterService.findAllPostings();
        return allPostings;
    }
    
    public List<User> getAllUsers() {
        List<User> allUsers = kwetterService.findAllUsers();
        return allUsers;
    }
    
     public void editUser(User u){
         this.facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext()
                .getRequestParameterMap();
        String username = params.get("username");
        String email = params.get("emailadress");
        String role = params.get("role");
        String location = params.get("location");
        
        u.setUserName(username);
        u.setEmailAdress(email);
        u.setUserRole(Role.valueOf(role));
        u.setLocation(location);
        
        kwetterService.editUser(u);
    }
    
    public void removeUser(User u){
        kwetterService.removeUser(u.getUserName());
    }
    
    public void removePosting(Posting p){
        kwetterService.removePosting(p.getId());
    }
}
