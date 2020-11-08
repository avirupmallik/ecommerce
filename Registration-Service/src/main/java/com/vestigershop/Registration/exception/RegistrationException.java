package com.vestigershop.Registration.exception;

public class RegistrationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929782750327461596L;
	
	public RegistrationException() {
        super();
    }

	public RegistrationException(String message) {
        super(message);
    }
	
	public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

   
    public RegistrationException(Throwable cause) {
        super(cause);
    }

}
