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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONArray;

import tu.kielce.pl.photoGallery.dto.ImageDTO;
import tu.kielce.pl.photoGallery.dto.ImageDetailsDTO;
import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.filter.Secured;
import tu.kielce.pl.photoGallery.manager.Authenticator;
import tu.kielce.pl.photoGallery.manager.ImageManager;
import tu.kielce.pl.photoGallery.model.Image;

@Path("image")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ImageRestEndpoint {

	@EJB
	ImageManager imageManager;
	@EJB
	Authenticator auth;
	
	@Path("/")
	@GET
	@Secured
	public Response getAllImageNames() {
		List<String> names = imageManager.getAllImageNames();
		return Response.ok(names).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Secured
	public Response getImage(@PathParam("id") String id) {
		File image = null;
		try {
			image = imageManager.loadImage(id);
			return Response.ok(image, MediaType.APPLICATION_OCTET_STREAM)
					.header("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"").build();
		} catch (IOException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@Path("/details/{id}")
	@GET
	@Secured
	public Response getImageDetails(@PathParam("id") String id) {
		Image image;
		try {
			image = imageManager.getImage(id);
		} catch (EntityNotFound e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(new ImageDetailsDTO(image)).build();
	}

	@Path("/")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	@Secured
	public Response uploadImage(MultipartFormDataInput input, @Context HttpHeaders headers) {
		System.out.println("It's alive!");
		ImageDTO imageDTO = new ImageDTO();
		String userName = auth.getUser((headers.getRequestHeader("Authorization").get(0)));
		imageDTO.setUser(userName);
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> categoryInputParts = uploadForm.get("category");
		List<InputPart> tagsInputParts = uploadForm.get("tags");
		List<InputPart> fileInputParts = uploadForm.get("image");
		List<InputPart> titleInputParts = uploadForm.get("title");
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
			for (InputPart part : titleInputParts) {
				System.out.println(part.getBodyAsString());
				String[] tmp = part.getBodyAsString().split("\\.");
				imageDTO.setFilename(tmp[0]);
				imageDTO.setExtension(tmp[1]);
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

	@Path("/size/{sizeMin}/{sizeMax}")
	@GET
	@Secured
	public Response searchBySize(@PathParam("sizeMin") int minSize, @PathParam("sizeMax") int maxSize) {
		List<String> names = imageManager.getImagesBySize(minSize, maxSize);
		return Response.ok(names).build();
	}

	@Path("/user/{userID}")
	@GET
	@Secured
	public Response searchByUser(@PathParam("userID") String userID) {
		List<String> names = imageManager.getImagesByUsername(userID);
		return Response.ok(names).build();
	}

	@Path("/extension/{extension}")
	@GET
	@Secured
	public Response searchByExtension(@PathParam("extension") String extension) {
		List<String> names = imageManager.getImagesByExtension(extension);
		return Response.ok(names).build();
	}

	@Path("/tag/{tag}")
	@GET
	@Secured
	public Response searchByTag(@PathParam("tag") String tag) {
		List<String> names = imageManager.getImagesByTag(tag);
		return Response.ok(names).build();
	}

	@Path("/tags/{tags: .*}")
	@GET
	@Secured
	public Response searchByMultipleTags(@PathParam("tags") List<PathSegment> tags) {
		List<String> tagNames = new ArrayList<>();
		for (PathSegment seg : tags) {
			tagNames.add(seg.getPath());
		}
		if(tagNames.size()==0){
			return Response.ok(tagNames).build();
		}
		List<String> names = imageManager.getImagesByMultipleTags(tagNames);
		return Response.ok(names).build();
	}

	@Path("/category/{category}")
	@GET
	@Secured
	public Response searchByCategory(@PathParam("category") String category) {
		List<String> names = imageManager.getImagesByCategory(category);
		return Response.ok(names).build();
	}
}
