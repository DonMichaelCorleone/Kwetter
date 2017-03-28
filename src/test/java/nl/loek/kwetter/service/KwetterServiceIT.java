///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.loek.kwetter.service;
//
//import java.sql.SQLException;
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
//import nl.loek.kwetter.util.DatabaseCleaner;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Loek
// */
//public class KwetterServiceIT {
//
//    final String userBaseURL = "http://localhost:8080/Kwetter/resources/api";
//    Client client;
//
//    private User user;
//    private Response response;
//
//    private DatabaseCleaner db;
//
//    public KwetterServiceIT() {
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
//    public void setUp() throws SQLException {
//        client = ClientBuilder.newClient();
//        user = new User("testuser", "pass");
//        db = new DatabaseCleaner();
//        db.clean();
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createUser method, of class KwetterService.
//     */
//    @Test
//    public void testCreateUser() throws Exception {
//        System.out.println("testCreateUser");
//
//        // CREATE USER TEST
//        Form form = new Form();
//        form.param("username", user.getUserName());
//        form.param("password", user.getPassword());
//        WebTarget root = client.target(userBaseURL + "/user");
//
//        response = root.request().post(Entity.form(form));
//        String responseText = response.readEntity(String.class);
//        assertThat(response.getStatus(), is(200));
//        assertEquals("true", responseText);
//
//    }
//
//    @Test
//    public void testEditUser() {
//        System.out.println("testEditUser");
//
//        // EDIT USER TEST
//        // 1 : username
//        // 2 : password
//        // 3 : bio
//        // 4 : location
//        // 5 : website
//        // 6 : profilephoto
//        WebTarget root = client.target(userBaseURL + "/user/edit");
//
//        Form form = new Form();
//        form.param("username", "testuser");
//        form.param("password", "pass");
//        form.param("fullname", "Loek Delahaye");
//        form.param("location", "Roermond");
//        form.param("biography", "180 chars long");
//        form.param("websiteURL", "http://www.test.com");
//
//        response = root.request().post(Entity.form(form));
//        String responseText = response.readEntity(String.class);
//        assertThat(response.getStatus(), is(200));
//        assertEquals("true", responseText);
//
//    }
//    
////    @Test
////    public void testGetUser(){
////        
////        WebTarget root = client.target(userBaseURL + "/user/" + user.getUserName());
////        
////        response = root.request().get();
////        String responseText = response.readEntity(String.class);
////        assertThat(response.getStatus(), is(200));
////        assertEquals("true", responseText);
////    }
//};
