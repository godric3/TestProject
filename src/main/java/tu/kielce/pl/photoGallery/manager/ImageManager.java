package tu.kielce.pl.photoGallery.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import tu.kielce.pl.photoGallery.dao.CategoryDAO;
import tu.kielce.pl.photoGallery.dao.ImageDAO;
import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.dao.TagImageDAO;
import tu.kielce.pl.photoGallery.dao.UserDAO;
import tu.kielce.pl.photoGallery.dto.ImageDTO;
import tu.kielce.pl.photoGallery.dto.SavedImageDTO;
import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.Image;
import tu.kielce.pl.photoGallery.model.Tag;
import tu.kielce.pl.photoGallery.model.TagImage;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class ImageManager {

	@EJB
	ImageDAO imageDAO;
	@EJB
	TagDAO tagDAO;
	@EJB
	CategoryDAO categoryDAO;
	@EJB
	TagImageDAO tagImageDAO;
	@EJB
	UserDAO userDAO;
	private static final String rootPath = new File("").getAbsolutePath() + "\\___images";

	public List<String> getAllImageNames() {
		List<String> names = imageDAO.getAllNames();
		return names;
	}

	public Image getImage(String name) throws EntityNotFound {
		return imageDAO.getByName(name.split("\\.")[0], name.split("\\.")[1]);
	}

	public void uploadImage(ImageDTO imageDTO) throws IOException {
		Image image = new Image();
		Category category = categoryDAO.getByNameOrCreate(imageDTO.getCategory());
		image.setCategoryName(category);
		image.setExtension(imageDTO.getExtension());
		image.setUrl(imageDTO.getFilename());
		image = imageDAO.create(image);
		Tag tmpTag;
		for (String tag : imageDTO.getTags()) {
			tmpTag = tagDAO.getByNameOrCreate(tag);
			TagImage tagImage = new TagImage();
			tagImage.setImage(image);
			tagImage.setTag(tmpTag);
			tagImageDAO.create(tagImage);
		}
		// image.setUrl(image.getId()+"");
		SavedImageDTO savedImageDTO = saveToFile(imageDTO.getInputStream(), rootPath, image.getUrl(),
				image.getExtension());
		image.setSize(savedImageDTO.getSize());
		image.setHeight(savedImageDTO.getHeight());
		image.setWidth(savedImageDTO.getWidth());
		User user = null;
		try {
			user = userDAO.getByUsername(imageDTO.getUser());
		} catch (EntityNotFound e) {
			System.out.println("SHOULD NOT HAPPEN");
			e.printStackTrace();
		}
		image.setUserId(user);
	}

	/* source: https://javatutorial.net/ */
	private SavedImageDTO saveToFile(InputStream inStream, String target, String fileName, String extension)
			throws IOException {
		File file = new File(target);
		file.mkdirs();
		String name = file.getPath() + "\\" + fileName + "." + extension;

		BufferedImage bi = ImageIO.read(inStream); // TODO: get width/height
		File outputfile = new File(name);
		ImageIO.write(bi, extension, outputfile);
		SavedImageDTO dto = new SavedImageDTO();
		dto.setSize((int) outputfile.length());
		dto.setHeight(bi.getHeight());
		dto.setWidth(bi.getWidth());
		System.out.println("Saved " + name);
		return dto;
	}

	public File loadImage(String url) throws IOException {
		File file;
		file = new File(rootPath);
		file.mkdirs();
		String name = file.getPath() + "\\" + url;
		file = new File(name);
		return file;
	}

	public List<String> getImagesBySize(int minSize, int maxSize) {
		List<String> names = imageDAO.getBySize(minSize, maxSize);
		return names;
	}

	public List<String> getImagesByUsername(String userName) {
		User user;
		try {
			user = userDAO.getByUsername(userName);
		} catch (EntityNotFound e) {
			return new ArrayList<>();
		}
		List<String> names = imageDAO.getByUser(user);
		return names;
	}

	public List<String> getImagesByTag(String tagName) {
		Tag tag;
		try {
			tag = tagDAO.find(tagName);
		} catch (EntityNotFound e) {
			return new ArrayList<>();
		}
		List<String> names = imageDAO.getByTag(tag);
		return names;
	}

	public List<String> getImagesByMultipleTags(List<String> tagNames) {
		List<Tag> tags;
		tags = tagDAO.getByNames(tagNames);
		if(tags.isEmpty()){
			return new ArrayList<>();
		}
		List<String> names = imageDAO.getByMultipleTags(tags);
		return names;
	}

	public List<String> getImagesByExtension(String extension) {
		List<String> names = imageDAO.getByExtension(extension);
		return names;
	}

	public List<String> getImagesByCategory(String categoryName) {
		Category category;
		try {
			category = categoryDAO.find(categoryName);
		} catch (EntityNotFound e) {
			return new ArrayList<>();
		}
		List<String> names = imageDAO.getByCategory(category);
		return names;
	}

}
