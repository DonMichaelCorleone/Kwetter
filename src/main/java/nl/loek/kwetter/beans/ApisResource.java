/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
import nl.loek.kwetter.dto.PostingDTO;
import nl.loek.kwetter.dto.UserDTO;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;
import org.modelmapper.ModelMapper;

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

    private ModelMapper modelMapper;

    private ObjectMapper mapper;

    /**
     * Creates a new instance of ApisResource
     */
    public ApisResource() {
        mapper = new ObjectMapper();
        modelMapper = new ModelMapper();
    }

    ///EVERYHING USER///
    /**
     * Gets all Users
     *
     * @return JSON(List<User>)
     * @throws JsonProcessingException
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() throws JsonProcessingException {
        return mapper.writeValueAsString(this.convertUsersToDto(kwetterService.findAllUsers()));
    }

    @GET
    @Path("user/{username}")
    @Produces("application/json")
    public UserDTO getUserByName(@PathParam("username") String username) {
        return this.convertUserToDto(kwetterService.findByUsername(username));
    }

    /**
     * Creates a new user instance
     *
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
    public String addUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            User u = new User(username, password);
            return Boolean.toString(kwetterService.createUser(u));
        } catch (InternalServerErrorException e) {
            return e.getCause().toString();
        }
    }

    /**
     * EditUser
     *
     * @param username
     * @param password
     * @param fullname
     * @param location
     * @param websiteURL
     * @param biography
     * @return
     */
    @POST
    @Path("user/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("fullname") String fullname, @FormParam("location") String location, @FormParam("biography") String biography, @FormParam("websiteURL") String websiteURL) {
        User u = kwetterService.findByUsername(username);
        u.setBiography(biography);
        u.setLocation(location);
        u.setPassword(password);
        u.setProfilePicture(websiteURL);
        u.setWebsiteURL(websiteURL);
        return Boolean.toString(kwetterService.editUser(u));
    }

    /**
     *
     * @param username
     * @param password
     * @param fullname
     * @param location
     * @param websiteURL
     * @param biography
     * @return
     */
    @GET
    @Path("user/remove/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeUser(@PathParam("username") String username) {
        return Boolean.toString(kwetterService.removeUser(username));
    }

    @POST
    @Path("user/edit/setPassword")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String setPassword(@FormParam("password") String password, @FormParam("username") String username) {
        User u = kwetterService.findByUsername(username);
        u.setPassword(password);
        return Boolean.toString(kwetterService.editUser(u));
    }

    @POST
    @Path("user/edit/addFollower/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void addFollower(@FormParam("username") String username, @FormParam("follower") String follower) {
        User u = kwetterService.findByUsername(username);
        User followUser = kwetterService.findByUsername(follower);
        u.addFollower(followUser);
        followUser.addFollowing(u);
        kwetterService.editUser(u);
        kwetterService.editUser(followUser);
    }

    @GET
    @Path("user/countFollowers/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public int countFollowers(@PathParam("username") String username) {
        Long id = kwetterService.findByUsername(username).getId();
        return kwetterService.countFollowers(id);
    }

    @GET
    @Path("user/countFollowing/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public int countFollowing(@PathParam("username") String username) {
        Long id = kwetterService.findByUsername(username).getId();
        return kwetterService.countFollowing(id);
    }

    @GET
    @Path("user/findAllFollowers/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String findAllFollowers(@PathParam("username") String username) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        return mapper.writeValueAsString(this.convertUsersToDto(u.getFollowers()));
    }

    @GET
    @Path("user/findAllFollowing/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String findAllFollowing(@PathParam("username") String username) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        return mapper.writeValueAsString(this.convertUsersToDto(u.getFollowing()));
    }

    @POST
    @Path("user/post/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postTweet(@FormParam("username") String username, @FormParam("title") String title, @FormParam("content") String content) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        Posting p = new Posting(u, title, content);
        return mapper.writeValueAsString(kwetterService.createPosting(p));
    }

    @GET
    @Path("post/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removePost(@PathParam("id") Long id) {
        return Boolean.toString(kwetterService.removePosting(id));
    }

    @GET
    @Path("user/getAllPosts/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPosts(@PathParam("username") String username) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        return mapper.writeValueAsString(this.convertPostingsToDto(kwetterService.findTweetsByUser(u)));
    }

    @POST
    @Path("post/addcomment/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String addComment(@FormParam("content") String content, @FormParam("posting") Long postingid, @FormParam("username") String username) throws JsonProcessingException {
        Posting p = kwetterService.findPosting(postingid);
        User author = kwetterService.findByUsername(username);
        p.addComment(content, author);
        return mapper.writeValueAsString(kwetterService.editPosting(p));
    }

    private List<PostingDTO> convertPostingsToDto(List<Posting> postings) {
        List<PostingDTO> postingDTOList = new ArrayList<PostingDTO>();
        PostingDTO postingDto = null;
        for (Posting p : postings) {
            postingDto = modelMapper.map(p, PostingDTO.class);
            postingDTOList.add(postingDto);
        }
        return postingDTOList;
    }

    private UserDTO convertUserToDto(User user) {
        UserDTO userDto = null;
        if (user != null) {
            userDto = modelMapper.map(user, UserDTO.class);
        }
        return userDto;
    }

    private List<UserDTO> convertUsersToDto(List<User> users) {
        List<UserDTO> userDtoList = new ArrayList<UserDTO>();
        UserDTO userDto = null;
        for (User u : users) {
            userDto = modelMapper.map(u, UserDTO.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
