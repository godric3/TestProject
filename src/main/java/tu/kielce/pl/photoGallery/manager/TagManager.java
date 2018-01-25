package tu.kielce.pl.photoGallery.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.dto.TagDTO;
import tu.kielce.pl.photoGallery.model.Tag;

@Stateless
public class TagManager {
	@EJB
	TagDAO tagDAO;

	public List<TagDTO> getAllTags() {
		List<Tag> tags = tagDAO.getAll();
		List<TagDTO> tagDTOs = new ArrayList<>();
		for (Tag t : tags) {
			TagDTO dto = new TagDTO();
			dto.setName(t.getName());
			// TODO: This may have poor performance, better to count entry in
			// TagImage?
			dto.setNumberOfImages(t.getTagImages().size());
			tagDTOs.add(dto);
		}
		return tagDTOs;
	}
}
