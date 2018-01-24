package tu.kielce.pl.photoGallery.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("image")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ImageRestEndpoint {

	@Path("/{id}")
	@GET
	public Response getImage(@PathParam("id") int id){
		
		return Response.ok().build();
	}
	@Path("/details/{id}")
	@GET
	public Response getImageDetails(@PathParam("id") int id){
		
		return Response.ok().build();
	}
	
	@Path("/")
	@POST
	public Response uploadImage(@PathParam("id") int id){
		
		return Response.ok().build();
	}
	@Path("/size/{size}")
	@GET
	public Response searchBySize(@PathParam("size") int size){
		
		return Response.ok().build();
	}
	@Path("/user/{userID}")
	@GET
	public Response searchByUser(@PathParam("userID") int userID){
		
		return Response.ok().build();
	}
	@Path("/extension/{extension}")
	@GET
	public Response searchByExtension(@PathParam("extension") String extension){
		
		return Response.ok().build();
	}
	@Path("/tag/{tag}")
	@GET
	public Response searchByTag(@PathParam("tag") String tag){
		
		return Response.ok().build();
	}
	@Path("/category/{category}")
	@GET
	public Response searchByCategory(@PathParam("category") String category){
		
		return Response.ok().build();
	}
}
