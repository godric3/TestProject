package tu.kielce.pl.photoGallery.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.dao.CategoryDAO;
import tu.kielce.pl.photoGallery.dto.CategoryDTO;
import tu.kielce.pl.photoGallery.dto.TagDTO;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.Tag;

@Stateless
public class CategoryManager {
	@EJB
	CategoryDAO categoryDAO;

	public List<CategoryDTO> getAllCategories() {
		List<Category> _categories = categoryDAO.getAll();
		List<CategoryDTO> categoriesDTOs = new ArrayList<>();
		for (Category c : _categories) {
			CategoryDTO dto = new CategoryDTO();
			dto.setName(c.getName());
			// TODO: This may have poor performance, better to count entry in
			// TagImage?
			dto.setNumberOfImages(c.getImages().size());
			categoriesDTOs.add(dto);
		}
		return categoriesDTOs;
	}
}
