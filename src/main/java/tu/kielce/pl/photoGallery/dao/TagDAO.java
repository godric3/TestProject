package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.model.Tag;

@Stateless
public class TagDAO extends GenericDAO<Tag> {

	@Override
	protected Class<?> getClassType() {
		return Tag.class;
	}

}
