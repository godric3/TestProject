package tu.kielce.pl.photoGallery.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tu.kielce.pl.photoGallery.manager.TestManager;
import tu.kielce.pl.photoGallery.model.TestEntity;

/*
 * Example REST endpoint
 * default path start at "http://localhost:8080/TestProject/api/"
 */

@Path("/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class HelloWorld {

	
	@EJB
	TestManager testManager;
	
	@GET
	@Path("/")
	public String helloWorld() {
		return  "Hello World!" ;
	}

	@GET
	@Path("/response")
	public Response helloWorldResponse() {
		return Response.ok("Hello World!").build();
	}
	
	@GET
	@Path("/responseWithHeader")
	public Response helloWorldResponseWithHeader() {
		return Response.ok("Hello World!").header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/notFoundResponse")
	public Response helloWorldNotFoundResponse() {
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/notFoundResponseWithText")
	public Response helloWorldNotFoundResponseWithText() {
		return Response.status(Status.NOT_FOUND).entity("Could not found").build();
	}
	
	@GET
	@Path("/create")
	public Response create() {
		TestEntity testEntity=new TestEntity();
		testEntity.setName("test");
		testManager.save(testEntity);
		return Response.ok("created id: " + testEntity.getId() + " name: "+testEntity.getName()).build();
	}
	@GET
	@Path("/show/{id}")
	public Response show(@PathParam("id") int id) {
		TestEntity testEntity=testManager.find(id);
		if(testEntity == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok("found id: " + testEntity.getId() + " name: "+testEntity.getName()).build();
	}

}