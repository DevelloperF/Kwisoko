package bi.filao_s.shoppingbackend.dao;

import java.util.List;

import bi.filao_s.shoppingbackend.dto.Cart;
import bi.filao_s.shoppingbackend.dto.CartLine;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);

	public CartLine get(int id);

	public boolean add(CartLine cartLine);

	public boolean update(CartLine cartLine);

	public boolean delete(CartLine cartLine);

	// Other business methodrelated to the cart lines 
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId,int productId);
	
	// add a cart
		boolean updateCart(Cart cart);
	

}
