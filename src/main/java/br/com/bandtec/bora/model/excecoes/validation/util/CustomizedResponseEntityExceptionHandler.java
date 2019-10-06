package br.com.bandtec.bora.model.excecoes.validation.util;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bandtec.bora.model.excecoes.validation.ErrosDetalhe;
import br.com.bandtec.bora.model.service.notfound.NaoEncontreiUsuario;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrosDetalhe detalhe = new ErrosDetalhe(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detalhe, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NaoEncontreiUsuario.class)
	public final ResponseEntity<Object> handleUserNotFoundException(NaoEncontreiUsuario ex, WebRequest request) {
		ErrosDetalhe detalhe = new ErrosDetalhe(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detalhe, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrosDetalhe detalhe = new ErrosDetalhe(new Date(), "Validation Failed", ex.getBindingResult().toString());

		return new ResponseEntity<>(detalhe, HttpStatus.BAD_REQUEST);
	}

}
