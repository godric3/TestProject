package tu.kielce.pl.photoGallery.dto;

import java.io.InputStream;
import java.util.List;

public class ImageDTO {
	private String category;
	private InputStream inputStream;
	private List<String> tags;
	private String filename;
	private String extension;
	private String userName;

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUser() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}
}
