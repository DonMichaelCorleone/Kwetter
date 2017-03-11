///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.loek.kwetter.service;
//
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import nl.kwetter.model.Posting;
//import nl.kwetter.model.User;
//import nl.kwetter.service.KwetterService;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Loek
// */
//
//public class KwetterServiceTest {
//    
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPU");
//    EntityManager  em = emf.createEntityManager();
//    
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
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of findByUsername method, of class KwetterService.
//     */
//    @Test
//    public void testFindByUsername() {
//        System.out.println("findByUsername");
//        String username = "";
//        KwetterService instance = new KwetterService();
//        User expResult = null;
//        User result = instance.findByUsername(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createUser method, of class KwetterService.
//     */
//    @Test
//    public void testCreateUser() {
//        System.out.println("createUser");
//        User userInstance1 = new User("Henk");
//        KwetterService instance = new KwetterService();
//        instance.createUser(userInstance1);
//        assertNotNull(instance.findByUsername("Henk"));
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editUser method, of class KwetterService.
//     */
//    @Test
//    public void testEditUser() {
//        System.out.println("editUser");
//        User user = null;
//        KwetterService instance = new KwetterService();
//        instance.editUser(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeUser method, of class KwetterService.
//     */
//    @Test
//    public void testRemoveUser() {
//        System.out.println("removeUser");
//        User user = null;
//        KwetterService instance = new KwetterService();
//        instance.removeUser(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAllUsers method, of class KwetterService.
//     */
//    @Test
//    public void testFindAllUsers() {
//        System.out.println("findAllUsers");
//        KwetterService instance = new KwetterService();
//        List<User> expResult = null;
//        List<User> result = instance.findAllUsers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of countFollowers method, of class KwetterService.
//     */
//    @Test
//    public void testCountFollowers() {
//        System.out.println("countFollowers");
//        String username = "";
//        KwetterService instance = new KwetterService();
//        int expResult = 0;
//        int result = instance.countFollowers(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of countFollowing method, of class KwetterService.
//     */
//    @Test
//    public void testCountFollowing() {
//        System.out.println("countFollowing");
//        String username = "";
//        KwetterService instance = new KwetterService();
//        int expResult = 0;
//        int result = instance.countFollowing(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findTweetsByUser method, of class KwetterService.
//     */
//    @Test
//    public void testFindTweetsByUser() {
//        System.out.println("findTweetsByUser");
//        String username = "";
//        KwetterService instance = new KwetterService();
//        List<Posting> expResult = null;
//        List<Posting> result = instance.findTweetsByUser(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of countTweets method, of class KwetterService.
//     */
//    @Test
//    public void testCountTweets() {
//        System.out.println("countTweets");
//        String username = "";
//        KwetterService instance = new KwetterService();
//        int expResult = 0;
//        int result = instance.countTweets(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
