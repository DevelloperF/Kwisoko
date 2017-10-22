package bi.filao_s.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//L'annotation @ControllerAdvice permet que chaque page levant une exception sera redirigée ici 

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	// Permet de gérer les erreurs 404 NOT FOUND EXCEPTIONS
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not constructed");
		mv.addObject("errorDescription", "The page is you are looking for is not available now ! ");
		mv.addObject("title", "404 Error Page");
		return mv;
	}

	// Permet de gérer un produit qui n'existe pas
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not Available");
		mv.addObject("errorDescription", "The product you are looking for is not available right now ! ");
		mv.addObject("title", "Product unavailable");
		return mv;
	}

	// Permet de gérer un produit qui n'existe pas en général
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle", "Contact Your Administrator!!");
		
		
		/* only for debugging your application*/
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
						
		mv.addObject("errorDescription", sw.toString());
		
		mv.addObject("title", "Error");
		
		return mv;
	}

}
