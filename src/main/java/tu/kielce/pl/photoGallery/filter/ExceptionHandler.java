package tu.kielce.pl.photoGallery.filter;

import java.io.FileNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<FileNotFoundException> {

	@Override
	public Response toResponse(FileNotFoundException exception) {
		return Response.status(Status.NOT_FOUND).build();
	}

}
