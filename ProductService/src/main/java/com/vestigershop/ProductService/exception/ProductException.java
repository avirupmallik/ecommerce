package com.vestigershop.ProductService.exception;

public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929782750327461596L;
	
	public ProductException() {
        super();
    }

	public ProductException(String message) {
        super(message);
    }
	
	public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

   
    public ProductException(Throwable cause) {
        super(cause);
    }

}
