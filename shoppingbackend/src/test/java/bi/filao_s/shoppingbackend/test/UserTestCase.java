package bi.filao_s.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bi.filao_s.shoppingbackend.dao.UserDAO;
import bi.filao_s.shoppingbackend.dto.Address;
import bi.filao_s.shoppingbackend.dto.Cart;
import bi.filao_s.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("bi.filao_s.shoppingbackend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}
	/*
	 * @Test public void testAddUser() {
	 * 
	 * user = new User(); user.setFirstName("Hrithik");
	 * user.setLastName("Roshan"); user.setEmail("hr@gmail.com");
	 * user.setContactNumber("1234512345"); user.setRole("USER");
	 * user.setEnabled(true); user.setPassword("12345");
	 * 
	 * assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
	 * address.setAddressLineTwo("Near Kaabil Store");
	 * address.setCity("Mumbai"); address.setState("Maharashtra");
	 * address.setCountry("India"); address.setPostalCode("400001");
	 * address.setBilling(true); address.setUserId(user.getId());
	 * assertEquals("Failed to add the address!", true,
	 * userDAO.addAddress(address));
	 * 
	 * if (user.getRole().equals("USER")) { cart = new Cart();
	 * cart.setUser(user); assertEquals("Failed to add the billing address!",
	 * true, userDAO.addCart(cart)); } // add the shipping address address = new
	 * Address();
	 * address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	 * address.setAddressLineTwo("Near Kudrat Store");
	 * address.setCity("Mumbai"); address.setState("Maharashtra");
	 * address.setCountry("India"); address.setPostalCode("400001");
	 * address.setShipping(true); address.setUserId(user.getId());
	 * assertEquals("Failed to add the shipping address!", true,
	 * userDAO.addAddress(address));
	 * 
	 * //
	 * 
	 * //
	 * 
	 * // linked the address with the user
	 * 
	 * /* linked the cart with the user
	 * 
	 * // link the user with the cart user.setCart(cart);
	 * 
	 * // add the user // add the address
	 * 
	 * 
	 * /
	 * 
	 * 
	 * 
	 * }
	 * 
	 * // working for uni-directional /*
	 * 
	 * @Test public void testAddAddress() { user = userDAO.get(1);
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
	 * address.setAddressLineTwo("Near Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001");
	 * 
	 * address.setUser(user); // add the address }
	 * 
	 * @Test public void testUpdateCart() { user = userDAO.get(1); cart =
	 * user.getCart(); cart.setGrandTotal(10000); cart.setCartLines(1);
	 * assertEquals("Failed to update the cart!", true,
	 * userDAO.updateCart(cart)); }
	 * 
	 */
	// }

	/*
	 * @Test public void testAddUser() {
	 * 
	 * user = new User(); user.setFirstName("Hrithik");
	 * user.setLastName("Roshan"); user.setEmail("hr@gmail.com");
	 * user.setContactNumber("1234512345"); user.setRole("CUSTOMER");
	 * user.setEnabled(true); user.setPassword("12345");
	 * 
	 * if (user.getRole().equals("USER")) { cart = new Cart();
	 * cart.setUser(user);
	 * 
	 * // attach cart with the user user.setCart(cart); }
	 * assertEquals("Failed to add the user!", true, userDAO.addUser(user)); }
	 */
	/*
	 * @Test public void testUpdateCart() { // Fetch the user by its email
	 * user=userDAO.getByEmail("hr@gmail.com");
	 * 
	 * // Get cart of the user cart=user.getCart();
	 * cart.setGrandTotal(44444444); cart.setCartLines(2);
	 * assertEquals("Failed to update the cart!",
	 * true,userDAO.updateCart(cart)); }
	 */
//
	@Test
	public void testAddAdress() {
		// We need to add an user
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setPassword("12345");

		assertEquals("Failed to add the user!", true, userDAO.addUser(user));

//		// We are going to add the adress
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
    	address.setCountry("India");
    	address.setPostalCode("400001");
		address.setBilling(true);
//		// attach the user to the adress
//		address.setUser(user);
		address.setUserId(user.getId());
		assertEquals("Failed to add adress", true, userDAO.addAddress(address));
//
//		// We are also going to add the shipping address
//		 address = new Address();
//		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//		address.setAddressLineTwo("Near Kudrat Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		// set shipping to true
//		address.setShipping(true);
//		assertEquals("Failed to add shipping adress", true,userDAO.addAddress(address));
//
	}
//	@Test
//	public void testAddAdress(){
//		user=userDAO.getByEmail("hr@gmail.com");
//		 address = new Address();
//			address.setAddressLineOne("301/B Jadoo Society, Kishan Kanhaiya Nagar");
//			address.setAddressLineTwo("Near Kudrat Store");
//			address.setCity("NGOZI");
//			address.setState("Karanataka");
//			address.setCountry("India");
//			address.setPostalCode("400001");
//			// set shipping to true
//			address.setShipping(true);
//			assertEquals("Failed to add shipping adress", true,userDAO.addAddress(address));
//	
//		
//	}
/*	@Test
	public void testGetAdress(){
		user=userDAO.getByEmail("hr@gmail.com");
		assertEquals("Failed to fatch list of adress and size does not match",0,userDAO.listShippingAdresses(user).size());

		assertEquals("Failed to fatch list of billing and size does not match","Mumbai",userDAO.getBillingAdress(user).getCity());
	}*/
}
