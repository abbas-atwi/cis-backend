package br.uniamerica.cis.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.uniamerica.cis.domain.exception.BusinessRuleException;

@ControllerAdvice //monitora exceptions lançadas na camada controller
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<Object> handleBusinessError(BusinessRuleException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		var body = new ResponseApi(status.value(), OffsetDateTime.now(), ex.getMessage());
		return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var statusHttp = status.value();
		var dateAndTime = OffsetDateTime.now();
		var title = "Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente";
		var body = new ResponseApi(statusHttp, dateAndTime, title);
		var fields = new ArrayList<ResponseApi.Field>();
		
		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			
			var name = ((FieldError)erro).getField();
			var message = erro.getDefaultMessage();
			
			fields.add(new ResponseApi.Field(name, message));
		}
		
		body.setFields(fields);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
}
