package bi.filao_s.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bi.filao_s.shoppingbackend.dao.UserDAO;
import bi.filao_s.shoppingbackend.dto.Address;
import bi.filao_s.shoppingbackend.dto.Cart;
import bi.filao_s.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address adress) {
		try {
			sessionFactory.getCurrentSession().persist(adress);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email=:email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAdress(User user) {
		String seletcQuery = "FROM Address WHERE user= :user AND billing = :billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(seletcQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAdresses(User user) {

		String seletcQuery = "FROM Address WHERE user= :user AND shipping = :shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(seletcQuery, Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
