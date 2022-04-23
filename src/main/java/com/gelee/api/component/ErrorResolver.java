package com.gelee.api.component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gelee.api.exceptions.BadRequestException;
import com.gelee.api.exceptions.ErrorResponse;
import com.gelee.api.exceptions.NoDataFoundException;
import com.gelee.api.util.SpecialCharacterConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que maneja las excepciones controladas.
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@RestControllerAdvice
@Slf4j
public class ErrorResolver {

	/**
	 * Método para logear el detalle de la excepción.
	 */
	private static void writeToLog(ErrorResponse errorResponse, Exception exception) {

		String message = Objects.isNull(exception) ? SpecialCharacterConstants.EMPTY_STRING : exception.getMessage();

		log.error(message, exception);

	}

	/**
	 * Metodo para manejar una excepcion de tipo BadRequestException.
	 *
	 * @param req Objeto Http Servlet de petición.
	 * @param ex  Excepción recibida BadRequestException.
	 * @return errorResponse Objeto de respuesta específica para
	 *         BadRequestException.
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resolveBadRequestException(HttpServletRequest req, BadRequestException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		List<String> badFields = ex.getBadFields();

		String moreinfo = null;

		if (Objects.nonNull(badFields) && !badFields.isEmpty()) {
			moreinfo = String.join(SpecialCharacterConstants.COMMA_SEPARATOR, badFields);
		}

		errorResponse.setStatus("ERROR");
		List<String> list = Stream.of(ex.getMessage()).collect(Collectors.toList());
		errorResponse.setErrors(list);

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;

	}

	/**
	 * Handler para manejar la excepcion {@link NoDataFoundException}.
	 *
	 * @param req Objeto Http Servlet de petición.
	 * @param ex  Excepción recibida NoDataFoundException.
	 * @return errorResponse Objeto de respuesta específica para
	 *         NoDataFoundException.
	 */
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse resolveNoDataFoundException(HttpServletRequest req, NoDataFoundException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setStatus("ERROR");
		List<String> list = Stream.of("Registro no encontrado").collect(Collectors.toList());
		errorResponse.setErrors(list);

		return errorResponse;

	}

	/**
	 * Metodo para manejar una excepcion de tipo {@link Exception}.
	 *
	 * @param req Objeto Http Servlet de petición.
	 * @param ex  Excepción recibida Exception.
	 * @return errorResponse Objeto de respuesta específica para Exception.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorResponse accessException(HttpServletRequest req, Exception ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setStatus("ERROR");
		List<String> list = Stream.of("Ocurrio un error. ").collect(Collectors.toList());
		errorResponse.setErrors(list);

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;

	}
	
	/**
	 * Metodo para manejar una excepcion de tipo {@link AccessDeniedException}.
	 *
	 * @param req Objeto Http Servlet de petición.
	 * @param ex  Excepción recibida Exception.
	 * @return errorResponse Objeto de respuesta específica para AccessDeniedException.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse resolveException(HttpServletRequest req, Exception ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setStatus("ERROR");
		List<String> list = Stream.of("Ocurrio un error. ").collect(Collectors.toList());
		errorResponse.setErrors(list);

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;

	}

}
