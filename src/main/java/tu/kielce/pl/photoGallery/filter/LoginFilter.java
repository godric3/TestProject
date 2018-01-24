package tu.kielce.pl.photoGallery.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import tu.kielce.pl.photoGallery.manager.Authenticator;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class LoginFilter implements ContainerRequestFilter{

	
	@Inject
	Authenticator auth;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String token=requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(!auth.validateToken(token)){
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
		
	}

}
