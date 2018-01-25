package tu.kielce.pl.photoGallery.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.filter.Secured;
import tu.kielce.pl.photoGallery.manager.TagManager;

@Path("/tag/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class TagRestEndpoint {

	@EJB
	TagManager tagManager;

	@Path("/All")
	@GET
	@Secured
	public Response getAllTags() {
		return Response.ok(tagManager.getAllTags()).build();// Can it fail?
	}
}
