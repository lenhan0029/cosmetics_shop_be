package com.cosmetics.cosmetics.Exception;

public class UnauthorizationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizationException() {
        super();
    }

    public UnauthorizationException(String message) {
        super(message);
    }

    public UnauthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizationException(Throwable cause) {
        super(cause);
    }
}
