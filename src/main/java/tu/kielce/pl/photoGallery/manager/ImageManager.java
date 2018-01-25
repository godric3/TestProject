package tu.kielce.pl.photoGallery.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

	public void uploadImage(ImageDTO imageDTO) {
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
	}

	/* source: https://javatutorial.net/ */
	private void saveToFile(InputStream inStream, String target, String fileName) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		File file = new File(target);
		file.mkdirs();
		String name = file.getPath() + "\\" + fileName;
		out = new FileOutputStream(name);
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

}
