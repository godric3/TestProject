package tu.kielce.pl.photoGallery.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import tu.kielce.pl.photoGallery.dao.CategoryDAO;
import tu.kielce.pl.photoGallery.dao.ImageDAO;
import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.dao.TagImageDAO;
import tu.kielce.pl.photoGallery.dto.ImageDTO;
import tu.kielce.pl.photoGallery.dto.SavedImageDTO;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.Image;
import tu.kielce.pl.photoGallery.model.Tag;
import tu.kielce.pl.photoGallery.model.TagImage;

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
	private static final String rootPath = new File("").getAbsolutePath() + "\\___images";

	public List<String> getAllImageNames() {
		List<String> names = imageDAO.getAllNames();
		return names;
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

}
