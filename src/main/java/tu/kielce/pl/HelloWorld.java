package tu.kielce.pl;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
public class HelloWorld {

	
	@EJB
	TestEJB ejb;
	
	@GET
	@Path("/")
	public Response helloWorld() {
		return Response.ok("Hello World!").build();
	}
	
	@GET
	@Path("/create")
	public Response create() {
		TestEntity testEntity=new TestEntity();
		testEntity.setName("test");
		ejb.persist(testEntity);
		return Response.ok("created id: " + testEntity.getId() + " name: "+testEntity.getName()).build();
	}
	@GET
	@Path("/show/{id}")
	public Response show(@PathParam("id") int id) {
		TestEntity testEntity=ejb.find(TestEntity.class, id);
		if(testEntity == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok("found id: " + testEntity.getId() + " name: "+testEntity.getName()).build();
	}

}
