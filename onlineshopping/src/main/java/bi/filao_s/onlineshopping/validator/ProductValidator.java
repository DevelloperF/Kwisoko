package bi.filao_s.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import bi.filao_s.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;
		// Wether file has been selected or not
		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {

			errors.rejectValue("file", null, "Please select an image file to uload!");
			return;
		}

		if (!(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png")
				|| product.getFile().getContentType().equals("image/gif"))) {
			errors.rejectValue("file", null, "Please use only image file for upload!");
			return;
		}
	}

}
