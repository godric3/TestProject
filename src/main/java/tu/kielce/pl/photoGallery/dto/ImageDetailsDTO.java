package tu.kielce.pl.photoGallery.dto;

import java.util.ArrayList;
import java.util.List;

import tu.kielce.pl.photoGallery.model.Image;
import tu.kielce.pl.photoGallery.model.Tag;
import tu.kielce.pl.photoGallery.model.TagImage;

public class ImageDetailsDTO {
	private String category;
	private List<String> tags;
	private String filename;
	private String extension;
	private int size;
	private int width;
	private int height;
	
	public ImageDetailsDTO(Image image){
		category=image.getCategory().getName();
		filename=image.getUrl();
		extension=image.getExtension();
		size=image.getSize();
		height=image.getHeight();
		width=image.getWidth();
		tags = new ArrayList<>();
		for(TagImage t:image.getTagImages()){
			tags.add(t.getTag().getName());
		}
	
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
