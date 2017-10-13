package bi.filao_s.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bi.filao_s.shoppingbackend.dao.ProductDAO;
import bi.filao_s.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	// Cette classe permet de retourner
	// le format JSON car il il n'y a pas du Model and View + l'annotation
	// @ResponseBody

	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProduct() {
		return productDAO.listActiveProducts();
	}

	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id) {
		return productDAO.listActiveProductsByCategory(id);
	}

}
