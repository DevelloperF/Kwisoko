package bi.filao_s.onlineshopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{

	/**
	 * @author FilaoNIYOMUKUNZI Jésus-Marie
	 */
	private static final long serialVersionUID = -4771564830758769831L;

	private String message;

	public ProductNotFoundException() {
		this("Product is not available");
	}

	public ProductNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ":" + message;
	}
	public String getMessage() {
		return message;
	}

}
