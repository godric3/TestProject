package tu.kielce.pl.photoGallery.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import tu.kielce.pl.photoGallery.dao.CategoryDAO;
import tu.kielce.pl.photoGallery.dao.ImageDAO;
import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.dao.TagImageDAO;
import tu.kielce.pl.photoGallery.dto.ImageDTO;
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
	private static final String rootPath=new File("").getAbsolutePath()+"\\___images";

	public void uploadImage(ImageDTO imageDTO) throws IOException {
		Image image = new Image();
		Category category = categoryDAO.getByNameOrCreate(imageDTO.getCategory());
		image.setCategoryName(category);
		image = imageDAO.create(image);
		Tag tmpTag;
		for (String tag : imageDTO.getTags()) {
			tmpTag = tagDAO.getByNameOrCreate(tag);
			TagImage tagImage = new TagImage();
			tagImage.setImage(image);
			tagImage.setTag(tmpTag);
			tagImageDAO.create(tagImage);
		}
		image.setUrl(image.getId()+"");
		int size=(int) saveToFile(imageDTO.getInputStream(), rootPath, image.getUrl(), "png");
		image.setSize(size);
	}

	/* source: https://javatutorial.net/ */
	private long saveToFile(InputStream inStream, String target, String fileName, String extension) throws IOException {
		File file = new File(target);
		file.mkdirs();
		String name = file.getPath() + "\\" + fileName;

	    BufferedImage bi = ImageIO.read(inStream);  //TODO: get width/height
	    File outputfile = new File(name);
	    ImageIO.write(bi, extension, outputfile);
		outputfile.length();
		System.out.println("Saved "+ name);
		return outputfile.length();
	}

	public File loadImage(String url) throws IOException{
		File file = new File(rootPath);
		file.mkdirs();
		String name = file.getPath() + "\\" + url;
		return new File(name);
	}

}
