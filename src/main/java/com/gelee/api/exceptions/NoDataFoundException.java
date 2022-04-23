package com.gelee.api.exceptions;

/**
 * Clase  custom NoDataFoundException
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
public class NoDataFoundException extends RuntimeException {
	
	 /**
	   * UID autogenerado para el versionado de la clase.
	   */
	  private static final long serialVersionUID = 5141264074683480037L;
	  
	  /**
	   * Constructor por defecto.
	   */
	  public NoDataFoundException() {
	    super();
	  }
	  
	  /**
	   * Constructor que recibe el mensaje de error que debera mostrarse.
	   * 
	   * @param message mensaje de la excepcion personalizado.
	   */
	  public NoDataFoundException(String message) {
	    super(message);
	  }

}
