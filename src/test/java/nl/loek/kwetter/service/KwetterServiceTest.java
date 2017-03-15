///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.loek.kwetter.service;
//
//import java.util.List;
//import javax.ejb.embeddable.EJBContainer;
//import nl.loek.kwetter.model.Posting;
//import nl.loek.kwetter.model.User;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Form;
//import javax.ws.rs.core.Response;
//import nl.loek.kwetter.dto.UserDTO;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Loek
// */
//public class KwetterServiceTest {
//    
//    final String userBaseURL = "http://localhost:8080/Kwetter/resources/api/";
//    Client client;
//     
//    public KwetterServiceTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        client = ClientBuilder.newClient();
//       
////       String username = "matthijs";
////       String password = "password";
//       
////       String usernameAndPassword = username + ":" + password;
////       authorizationHeaderName = "Authorization";
////       authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//     /**
//     * Test of createUser method, of class KwetterService.
//     */
//    @Test
//    public void testCreateUser() throws Exception {
//       
//        User user = new User("TestUser1" , "TestPassword");
//        WebTarget root = client.target(userBaseURL + "user");
//        Response response = root.request().get();
//        
//        // PUT RESULT IN INT
////        List<UserDTO> users = response.readEntity(List.class);
//        assertThat(response.getStatus(), is(200));
////        int startAmount = users.size();
////        
////        
////        // SAVE
////        Form form = new Form();
////        form.param("username", user.getUserName());
////        form.param("password", user.getPassword());
////        root = client.target(userBaseURL + "/save");
////        
////        // SAVE TEST
////         response = root.request().post(Entity.form(form));
////        String responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("User saved with succes!", responseText);
////        
////        response = root.request().post(Entity.form(form));
////        responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("User was not saved", responseText);
////        
////        User savedUser = DAO.getUsersByKeyword(user.getUserName()).get(0);
////        // EDIT
////        // 1 : username
////        // 2 : password
////        // 3 : bio
////        // 4 : location
////        // 5 : website
////        // 6 : profilephoto
////        root = client.target(userBaseURL + "/edit");
////        for(int i = 3; i <7; i++){
////            form = new Form();
////            form.param("id", savedUser.getUserID()+"");
////            form.param("field", i + "");
////            form.param("newvalue", "test " + i);
////            
////            response = root.request().header(authorizationHeaderName, authorizationHeaderValue).post(Entity.form(form));
////            responseText = response.readEntity(String.class);
////            assertThat(response.getStatus(), is(200));
////            assertEquals("User edited!", responseText);
////        }
////        // WRONG FIELD
////        form = new Form();
////        form.param("id", savedUser.getUserID()+ "");
////        form.param("field","7");
////        form.param("newvalue", "test 7");
////        response = root.request().header(authorizationHeaderName, authorizationHeaderValue).post(Entity.form(form));
////        responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("That field does not exist!", responseText);
////        
////        // WRONG USER
////        form = new Form();
////        form.param("id", "-1");
////        form.param("field","1");
////        form.param("newvalue", "test 8");
////        response = root.request().header(authorizationHeaderName, authorizationHeaderValue).post(Entity.form(form));
////        responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("There is no user with that ID!", responseText);        
////        
//////        // TEST WAAROM WERKT DIT NIET
//////        User editedUser = DAO.getUserByID(savedUser.getUserID());
//////        assertEquals("test 3", editedUser.getBio());
//////        assertEquals("test 4", editedUser.getLocation());
//////        assertEquals("test 5", editedUser.getWebsite());
//////        assertEquals("test 6", editedUser.getProfilePhoto());
////        
////        // DELETE
////        root = client.target(userBaseURL + "/delete");
////        form = new Form();
////        form.param("id", savedUser.getUserID()+ "");
////        response = root.request().header(authorizationHeaderName, authorizationHeaderValue).post(Entity.form(form));
////        responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("The user has been deleted", responseText);  
////        
////        // WRONG ID
////        form = new Form();
////        form.param("id", "-1");
////        response = root.request().header(authorizationHeaderName, authorizationHeaderValue).post(Entity.form(form));
////        responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("There is no user with that ID", responseText);  
////        
////        // THE USER COUNT SHOULD BE BACK TO NORMAL
////        root = client.target(userBaseURL + "/get");
////        response = root.request().get();
////        users = response.readEntity(List.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals(startAmount, users.size());
////        
//    }
//    
//
//    /**
//     * Test of findByUsername method, of class KwetterService.
//     */
////    @Test
////    public void testFindByUsername() throws Exception {
////        System.out.println("findByUsername");
////        String username = "";
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        User expResult = null;
////        User result = instance.findByUsername(username);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////   
////
////    /**
////     * Test of editUser method, of class KwetterService.
////     */
////    @Test
////    public void testEditUser() throws Exception {
////        System.out.println("editUser");
////        User user = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Boolean expResult = null;
////        Boolean result = instance.editUser(user);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of removeUser method, of class KwetterService.
////     */
////    @Test
////    public void testRemoveUser() throws Exception {
////        System.out.println("removeUser");
////        String username = "";
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Boolean expResult = null;
////        Boolean result = instance.removeUser(username);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findAllUsers method, of class KwetterService.
////     */
////    @Test
////    public void testFindAllUsers() throws Exception {
////        System.out.println("findAllUsers");
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        List<User> expResult = null;
////        List<User> result = instance.findAllUsers();
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of countFollowers method, of class KwetterService.
////     */
////    @Test
////    public void testCountFollowers() throws Exception {
////        System.out.println("countFollowers");
////        Long id = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        int expResult = 0;
////        int result = instance.countFollowers(id);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of countFollowing method, of class KwetterService.
////     */
////    @Test
////    public void testCountFollowing() throws Exception {
////        System.out.println("countFollowing");
////        Long id = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        int expResult = 0;
////        int result = instance.countFollowing(id);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findTweetsByUser method, of class KwetterService.
////     */
////    @Test
////    public void testFindTweetsByUser() throws Exception {
////        System.out.println("findTweetsByUser");
////        User u = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        List<Posting> expResult = null;
////        List<Posting> result = instance.findTweetsByUser(u);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findPosting method, of class KwetterService.
////     */
////    @Test
////    public void testFindPosting() throws Exception {
////        System.out.println("findPosting");
////        Long id = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Posting expResult = null;
////        Posting result = instance.findPosting(id);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of editPosting method, of class KwetterService.
////     */
////    @Test
////    public void testEditPosting() throws Exception {
////        System.out.println("editPosting");
////        Posting posting = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Boolean expResult = null;
////        Boolean result = instance.editPosting(posting);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of removePosting method, of class KwetterService.
////     */
////    @Test
////    public void testRemovePosting() throws Exception {
////        System.out.println("removePosting");
////        Long id = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Boolean expResult = null;
////        Boolean result = instance.removePosting(id);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of createPosting method, of class KwetterService.
////     */
////    @Test
////    public void testCreatePosting() throws Exception {
////        System.out.println("createPosting");
////        Posting p = null;
////        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
////        KwetterService instance = (KwetterService)container.getContext().lookup("java:global/classes/KwetterService");
////        Boolean expResult = null;
////        Boolean result = instance.createPosting(p);
////        assertEquals(expResult, result);
////        container.close();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////    
//}
