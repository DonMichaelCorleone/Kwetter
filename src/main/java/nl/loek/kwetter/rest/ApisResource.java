/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import nl.loek.kwetter.dto.PostingDTO;
import nl.loek.kwetter.dto.UserDTO;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;
import org.modelmapper.ModelMapper;
import org.primefaces.json.JSONObject;

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

    @GET
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PostingDTO> getAllPostings() throws JsonProcessingException {
        return this.convertPostingsToDto(kwetterService.findAllPostings());
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
    public List<UserDTO> getAllUsers() throws JsonProcessingException {
        return this.convertUsersToDto(kwetterService.findAllUsers());
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
    public String addUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("email") String email) {
        try {
            User u = new User(username, email, password);
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
    @Produces(MediaType.TEXT_PLAIN)
    public Response editUser(@FormParam("biography") String biography, @FormParam("location") String location, @FormParam("userName") String username) {
        try {
            User u = kwetterService.findByUsername(username);
            u.setBiography(biography);
            u.setLocation(location);
            u.setUserName(username);
            kwetterService.editUser(u);
            return Response.status(200).entity("true").build();
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity("false").build();
        }
    }

    @POST
    @Path("user/authenticate")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            User u = kwetterService.findByUsername(username);
            String receivedPass = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            
            if (u.getPassword().equals(receivedPass)) {
                return Response.status(200).entity(this.convertUserToDto(u)).build();
            } else {
                return Response.status(200).entity("false").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity("false").build();
        }
    }

    @POST
    @Path("user/post/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postTweet(@FormParam("username") String username, @FormParam("content") String content, @FormParam("title") String title) {
        try {
            Posting p = new Posting(username, title, content);
            return Response.status(200).entity(kwetterService.createPosting(p).toString()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(200).entity("false").build();
        }
    }

//    @POST
//    @Path("user/post/")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response postTweet(@FormParam("username") String username,
//            @FormParam("title") String title,
//            @FormParam("content") String content) throws JsonProcessingException {
//        try {
//            User u = kwetterService.findByUsername(username);
//            Posting p = new Posting(u.getUserName(), title, content);
//            this.kwetterService.createPosting(p);
//            return Response.status(200).entity(true).build();
//        } catch (IllegalArgumentException e) {
//            return Response.status(200).entity(false).build();
//        }
//    }
//        JSONObject myObject = new JSONObject(inputJsonObj);
//        return myObject.toString();
//        String username = (String) inputJsonObj.get("userName");
//        String location = (String) inputJsonObj.get("location");
//        String websiteURL = (String) inputJsonObj.get("websiteURL");
//        String biography = (String) inputJsonObj.get("biography");
//    }
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
    public String removeUser(@PathParam("username") String username
    ) {
        return Boolean.toString(kwetterService.removeUser(username));
    }

    @POST
    @Path("user/edit/setPassword")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String setPassword(@FormParam("password") String password,
            @FormParam("username") String username
    ) {
        User u = kwetterService.findByUsername(username);
        u.setPassword(password);
        return Boolean.toString(kwetterService.editUser(u));
    }

    @POST
    @Path("user/edit/addFollower/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void addFollower(@FormParam("username") String username,
            @FormParam("follower") String follower
    ) {
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
    public int countFollowers(@PathParam("username") String username
    ) {
        Long id = kwetterService.findByUsername(username).getId();
        return kwetterService.countFollowers(id);
    }

    @GET
    @Path("user/countFollowing/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public int countFollowing(@PathParam("username") String username
    ) {
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

    @GET
    @Path("post/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removePost(@PathParam("id") Long id
    ) {
        return Boolean.toString(kwetterService.removePosting(id));
    }

    @GET
    @Path("user/findAllConnectedPosts/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllConnectedPosts(@PathParam("username") String username) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        List<Posting> AllConnectedPosts = new ArrayList<Posting>();
        AllConnectedPosts.addAll(kwetterService.findTweetsByUser(u.getUserName()));
        for (User following : u.getFollowing()) {
            AllConnectedPosts.addAll(kwetterService.findTweetsByUser(following.getUserName()));
        }
        return mapper.writeValueAsString(this.convertPostingsToDto(AllConnectedPosts));
    }

    @GET
    @Path("user/getAllPosts/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPosts(@PathParam("username") String username) throws JsonProcessingException {
        User u = kwetterService.findByUsername(username);
        return mapper.writeValueAsString(this.convertPostingsToDto(kwetterService.findTweetsByUser(u.getUserName())));
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
