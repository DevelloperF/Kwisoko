package bi.filao_s.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bi.filao_s.shoppingbackend.dao.CategoryDAO;
import bi.filao_s.shoppingbackend.dto.Category;

//The @Repository annotation is a marker for class specifying that it fulfills the role of 
//providing access to the data and will be managed by spring framework.
//PERMET DE PASSER LES ELEMENTS RECUPERS A LA BASE DE DONNES categoryDAO=categoryDAO DU PageController

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImp implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		// C'est du HQL Le nom passé est celui de notre entité
		String selectActiveCategory = "FROM Category WHERE active=:active";

		// Cette requête provient de hibernate
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();

	}

	// Getting single category based on id
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {
			// Updating a single category
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try {
			// Updating a single category
			// Ce qu'une simple désactivation du produit sans l'effacer
			// completement
			category.setActive(false);
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
