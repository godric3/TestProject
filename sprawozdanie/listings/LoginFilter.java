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