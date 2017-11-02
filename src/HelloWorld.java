import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloWorld {

	@GET
	@Path("/")
	public Response helloWorld() {
		return Response.ok("Hello World!").build();
	}

}
