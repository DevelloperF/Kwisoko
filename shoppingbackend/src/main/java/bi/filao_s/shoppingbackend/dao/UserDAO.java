package bi.filao_s.shoppingbackend.dao;

import java.util.List;

import bi.filao_s.shoppingbackend.dto.Address;
import bi.filao_s.shoppingbackend.dto.Cart;
import bi.filao_s.shoppingbackend.dto.User;

public interface UserDAO {
	// add an user
	boolean addUser(User user);

	// avoir l'utilisateur
	User getByEmail(String email);

	// add an adress
	boolean addAddress(Address adress);

	Address getBillingAdress(User user);

	List<Address> listShippingAdresses(User user);

	// add a cart
	boolean updateCart(Cart cart);
}
