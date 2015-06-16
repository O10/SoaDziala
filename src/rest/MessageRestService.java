package rest;

/**
 * Created by O10 on 16.06.15.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by O10 on 16.06.15.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/message")
public class MessageRestService {

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {

        String result = "Restful example : " + msg;

        return Response.status(200).entity(result).build();

    }

}