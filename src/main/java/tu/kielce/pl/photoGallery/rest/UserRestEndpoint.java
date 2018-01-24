package tu.kielce.pl.photoGallery.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tu.kielce.pl.photoGallery.dto.UserDTO;
import tu.kielce.pl.photoGallery.manager.UserManager;
import tu.kielce.pl.photoGallery.model.User;

@Path("/user/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class UserRestEndpoint {


	@EJB
	UserManager userManager;

	@POST
	@Path("/")
	@Produces("application/json")
	public Response newUser(UserDTO userDto) {
		if(userDto == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		User user=null;
		user=userManager.registerUser(userDto.getUsername(), userDto.getPassword());//UserAlreadyExist
		if(user!=null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("NOT IMPLEMENTED").build();
		}
	}

	@POST
	@Path("/login")
	@Produces("application/json")
	public Response login(UserDTO userDto) {
		if(userDto == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		User user;
		String username=userDto.getUsername();
		String password=userDto.getPassword();
		try {
			user = userManager.loginUser(username, password);//UserNotExist, WrongPassword
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		if(user!=null){
			return Response.ok(user.getToken(), MediaType.APPLICATION_JSON).build();
		}else{
			return Response.status(Response.Status.NOT_FOUND).entity("User not found for login: " + username).build();
		}
	}
}
