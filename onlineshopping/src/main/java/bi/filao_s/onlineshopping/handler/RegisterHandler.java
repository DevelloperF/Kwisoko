package bi.filao_s.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import bi.filao_s.onlineshopping.model.RegisterModel;
import bi.filao_s.shoppingbackend.dao.UserDAO;
import bi.filao_s.shoppingbackend.dto.Address;
import bi.filao_s.shoppingbackend.dto.Cart;
import bi.filao_s.shoppingbackend.dto.User;

//Pour la faire un bean
@Component
public class RegisterHandler {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";

		// checkin if password matches confirm password

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match the confirm password!").build());
			transitionValue = "failure";
		}
		// Check the uniqueness the email id
		if (userDAO.getByEmail(user.getEmail()) != null) {

			error.addMessage(
					new MessageBuilder().error().source("email").defaultText("Email adress is already used!").build());

			transitionValue = "failure";
		}

		return transitionValue;
	}

	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		// Fetch the user
		User user = model.getUser();

		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}

		// Before saving the user; encode the password
        
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// save the user
		userDAO.addUser(user);

		// Get the adress
		Address billing = model.getBilling();

		billing.setUserId(user.getId());
		billing.setBilling(true);

		// Save the address
		userDAO.addAddress(billing);

		return transitionValue;

	}
}
