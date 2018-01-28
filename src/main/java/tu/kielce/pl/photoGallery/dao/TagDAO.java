package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Tag;

@Stateless
public class TagDAO extends GenericDAO<Tag> {

	@Override
	protected Class<?> getClassType() {
		return Tag.class;
	}

	public Tag getByNameOrCreate(String tagName) {
		Tag tag;
		try {
			tag = find(tagName);
		} catch (EntityNotFound e) {
			Tag t = new Tag();
			t.setName(tagName);
			tag = create(t);
		}
		return tag;
	}

	public List<Tag> getByNames(List<String> tagNames) {
		Query q = entityManager.createNamedQuery("Tag.findByMultipleNames", Tag.class);
		q.setParameter("names", tagNames);
		List<Tag> tags = q.getResultList();
		return tags;
	}

}
