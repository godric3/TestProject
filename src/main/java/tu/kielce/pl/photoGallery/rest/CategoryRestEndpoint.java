package tu.kielce.pl.photoGallery.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import tu.kielce.pl.photoGallery.filter.Secured;
import tu.kielce.pl.photoGallery.manager.CategoryManager;

@Path("/category/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class CategoryRestEndpoint {

	@EJB
	CategoryManager categoryManager;

	@Path("/")
	@GET
	@Secured
	public Response getAllCategories() {
		return Response.ok(categoryManager.getAllCategories()).build();// Can it fail?
	}
}
