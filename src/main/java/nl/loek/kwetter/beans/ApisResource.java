/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@RequestScoped
public class ApisResource {

    @Inject
    KwetterService kwetterService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApisResource
     */
    public ApisResource() {
    }

    /**
     * Retrieves representation of an instance of nl.loek.kwetter.beans.ApisResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<User> getXml() {
        return kwetterService.findAllUsers();
    }

    /**
     * POST method for creating an instance of ApiResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response postXml(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ApiResource getApiResource(@PathParam("id") String id) {
        return ApiResource.getInstance(id);
    }

    private KwetterService lookupKwetterServiceBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (KwetterService) c.lookup("java:global/nl.kwetter_Kwetter_war_1.0/KwetterService!nl.loek.kwetter.service.KwetterService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
