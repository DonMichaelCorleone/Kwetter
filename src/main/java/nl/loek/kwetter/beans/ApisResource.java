/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;

/**
 * REST Web Service
 *
 * @author Loek
 */
@Path("/api")
@Stateless
public class ApisResource {

    @Inject
    KwetterService kwetterService;

    private ObjectMapper mapper;
    /**
     * Creates a new instance of ApisResource
     */
    public ApisResource() {
         mapper = new ObjectMapper();
    }
    
    /**
     * Gets all Users
     * @return JSON(List<User>)
     * @throws JsonProcessingException 
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserJSON() throws JsonProcessingException {
    
        return mapper.writeValueAsString(kwetterService.findAllUsers());
    }
    
    @GET
    @Path("user/{username}")
    @Produces("application/json")
    public User getUserByName(@PathParam("username") String username){
        return kwetterService.findByUsername(username);
    }
    /**
     * Creates a new user instance
     * @param username
     * @param password
     * @param fullname
     * @param location
     * @param websiteURL
     * @param biography
     * @return Boolean value as String. 
     */
    @POST
    @Path("user")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("fullname") String fullname, @FormParam("location") String location, @FormParam("websiteURL") String websiteURL, @FormParam("biography") String biography){
        try{
            User u = new User(username, password, location, biography, websiteURL);
        return Boolean.toString(kwetterService.createUser(u));
        }catch(InternalServerErrorException e){
            return e.getCause().toString();
        }
    }
    
    @POST
    @Path("user/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("fullname") String fullname, @FormParam("location") String location, @FormParam("websiteURL") String websiteURL, @FormParam("biography") String biography){
        User u = new User(username, password, location, biography, websiteURL);
        return Boolean.toString(kwetterService.editUser(u));
    }
}
