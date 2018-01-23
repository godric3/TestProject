package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;
import javax.swing.text.html.HTML.Tag;

import tu.kielce.pl.photoGallery.model.Image;

@Stateless
public class TagDAO extends GenericDAO<Image> {

	@Override
	protected Class<?> getClassType() {
		return Tag.class;
	}

}
