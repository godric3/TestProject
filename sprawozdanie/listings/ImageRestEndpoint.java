@Path("image")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ImageRestEndpoint {

	@EJB
	ImageManager imageManager;

	@Path("/")
	@GET
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
	...
}