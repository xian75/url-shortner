package it.myurlshortner.resources;

import it.myurlshortner.exception.FacadeException;
import it.myurlshortner.facade.interfaces.IUrlsFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author NATCRI
 */
@Path("urls")
public class UrlsResource {

    @Inject
    protected IUrlsFacade facade;

    /**
     * Get long url by its short url
     *
     * @param shortUrl the short url to get
     * @return Response with status code: 200 "OK" and the related long url if
     * shortUrl exists; 204 "No Content" if shortUrl doesn't exists; 500
     * "Internal Server Error" if something went wrong.
     */
    @GET
    @Path("{shortUrl}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@PathParam("shortUrl") @NotNull String shortUrl) {
        try {
            String longUrl = facade.get(shortUrl);
            if (longUrl == null) {
                return Response.status(Status.NO_CONTENT).build();
            } else {
                return Response.ok(longUrl).build();
            }
        } catch (FacadeException ex) {
            Logger.getLogger(UrlsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    /**
     * Set a long url and return a unique short url
     *
     * @param longUrl the long url to set
     * @return Response with status code: 200 "OK" and the generated short url;
     * 400 "Bad Request" if longUrl is null; 500 "Internal Server Error" if
     * something went wrong.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response set(@FormParam("longUrl") @NotNull String longUrl) {
        try {
            return Response.ok(facade.set(longUrl)).build();
        } catch (FacadeException ex) {
            Logger.getLogger(UrlsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
}
