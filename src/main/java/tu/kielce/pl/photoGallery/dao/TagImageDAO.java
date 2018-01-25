package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.model.TagImage;

@Stateless
public class TagImageDAO extends GenericDAO<TagImage> {

	@Override
	protected Class<?> getClassType() {
		return TagImage.class;
	}

}
