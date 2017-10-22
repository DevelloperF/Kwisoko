package bi.filao_s.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bi.filao_s.onlineshopping.util.FileUploadUtility;
import bi.filao_s.onlineshopping.validator.ProductValidator;
import bi.filao_s.shoppingbackend.dao.CategoryDAO;
import bi.filao_s.shoppingbackend.dao.ProductDAO;
import bi.filao_s.shoppingbackend.dto.Category;
import bi.filao_s.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClikManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();

		// Set few of the field
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully");
			} else if (operation.equals("category")) {

				mv.addObject("message", "Category Submitted Successfully");

			}
		}
		return mv;
	}

	// Handling the management of the product
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClikManageProducts", true);
		mv.addObject("title", "Manage Products");

		// Fetch the product from the database
		Product nProduct = productDAO.get(id);

		// Set the product fetched from the database
		mv.addObject("product", nProduct);

		return mv;
	}

	// Handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		// For uploading the file
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		// Validating the image
		if (mProduct.getId() == 0) {

			new ProductValidator().validate(mProduct, results);

		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);

			}
		}
		// Check if there are any errors
		if (results.hasErrors()) {
			model.addAttribute("userClikManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for this Product");
			return "page";
		}

		logger.info(mProduct.toString());

		// Create a new product record
		if (mProduct.getId() == 0) {
			// Quand l'id est 0 c'est que c'est un nouveau produit
			productDAO.add(mProduct);
		} else {
			productDAO.update(mProduct);
		}

		// Permet la redirection
		return "redirect:/manage/products?operation=product";
	}

	// Pour l'activation et la désactivation des produits
	// L'ajax
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		// Is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		// Activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		// Updating the product
		productDAO.update(product);
		return (isActive) ? "You have successfully deactivated the product with id " + product.getId()
				: "You have successfully activated the product with id " + product.getId();
	}

	// To handle category submission
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		// Add the new category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";

	}

	// Returning categories for all the request maping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
