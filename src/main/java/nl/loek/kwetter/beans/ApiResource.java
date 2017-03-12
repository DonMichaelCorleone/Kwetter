///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.loek.kwetter.beans;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.DELETE;
//import javax.enterprise.context.RequestScoped;
//import javax.ws.rs.core.MediaType;
//
///**
// * REST Web Service
// *
// * @author Loek
// */
//@RequestScoped
//public class ApiResource {
//
//    private String id;
//
//    /**
//     * Creates a new instance of ApiResource
//     */
//    private ApiResource(String id) {
//        this.id = id;
//    }
//
//    /**
//     * Get instance of the ApiResource
//     */
//    public static ApiResource getInstance(String id) {
//        // The user may use some kind of persistence mechanism
//        // to store and restore instances of ApiResource class.
//        return new ApiResource(id);
//    }
//
//    /**
//     * Retrieves representation of an instance of nl.loek.kwetter.beans.ApiResource
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of ApiResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
//
//    /**
//     * DELETE method for resource ApiResource
//     */
//    @DELETE
//    public void delete() {
//    }
//}
