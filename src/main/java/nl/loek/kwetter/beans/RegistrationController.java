/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;

/**
 *
 * @author Loek
 */
@ManagedBean(name = "registrationController")
@ViewScoped
public class RegistrationController implements Serializable {
    
    @Inject
    private KwetterService kwetterService;
    
    FacesContext facesContext;
    /**
     * Creates a new instance of registrationController
     */
    public RegistrationController() {
    }
    
                                    
    public void registerAccount() throws IOException{
        this.facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext()
                .getRequestParameterMap();
        String username = params.get("username");
        String email = params.get("email");
        String password = params.get("password");
        
        
//        if(this.kwetterService.findByUsername(username) != null){
//            
//        }else{
            User u = new User(username,email,password);
            this.kwetterService.createUser(u);
            this.facesContext.getExternalContext().redirect("http://localhost:8080/Kwetter/Home/");
//        }
    }
    
}
