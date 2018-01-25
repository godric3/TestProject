package tu.kielce.pl.photoGallery.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONArray;

import tu.kielce.pl.photoGallery.dto.ImageDTO;
import tu.kielce.pl.photoGallery.filter.Secured;
import tu.kielce.pl.photoGallery.manager.ImageManager;

@Path("image")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ImageRestEndpoint {

	@EJB
	ImageManager imageManager;

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Secured
	public Response getImage(@PathParam("id") String id) {
		File image=null;
		try {
			image = imageManager.loadImage(id);
		} catch (IOException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(image, MediaType.APPLICATION_OCTET_STREAM).header("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"" ).build();
	}

	@Path("/details/{id}")
	@GET
	public Response getImageDetails(@PathParam("id") int id) {
		return Response.ok().build();
	}

	@Path("/")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	@Secured
	public Response uploadImage(MultipartFormDataInput input) {
		System.out.println("It's alive!");
		ImageDTO imageDTO = new ImageDTO();
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> categoryInputParts = uploadForm.get("category");
		List<InputPart> tagsInputParts = uploadForm.get("tags");
		List<InputPart> fileInputParts = uploadForm.get("image");
		System.out.println(categoryInputParts.size());
		try {
			for (InputPart part : categoryInputParts) {
				imageDTO.setCategory(part.getBodyAsString());
			}
			for (InputPart part : tagsInputParts) {
				JSONArray a = new JSONArray(part.getBodyAsString());
				List<String> tags = new ArrayList<>();
				for (Object o : a.toList()) {
					tags.add(Objects.toString(o));
				}
				imageDTO.setTags(tags);
			}
			for (InputPart part : fileInputParts) {
				imageDTO.setInputStream(part.getBody(InputStream.class, null));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		try {
			imageManager.uploadImage(imageDTO);
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok().build();
	}

	@Path("/size/{size}")
	@GET
	public Response searchBySize(@PathParam("size") int size) {
		return Response.ok().build();
	}

	@Path("/user/{userID}")
	@GET
	public Response searchByUser(@PathParam("userID") int userID) {
		return Response.ok().build();
	}

	@Path("/extension/{extension}")
	@GET
	public Response searchByExtension(@PathParam("extension") String extension) {
		return Response.ok().build();
	}

	@Path("/tag/{tag}")
	@GET
	public Response searchByTag(@PathParam("tag") String tag) {
		return Response.ok().build();
	}

	@Path("/category/{category}")
	@GET
	public Response searchByCategory(@PathParam("category") String category) {
		return Response.ok().build();
	}
}
