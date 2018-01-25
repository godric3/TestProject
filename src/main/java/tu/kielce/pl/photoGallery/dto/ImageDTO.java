package tu.kielce.pl.photoGallery.dto;

import java.io.InputStream;
import java.util.List;

public class ImageDTO {
	private String category;
	private InputStream inputStream;
	private List<String> tags;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream file) {
		this.inputStream = file;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
