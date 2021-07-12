package com.user_management_multidb_api.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		    LocalDateTime   dateTime = LocalDateTime.now();

		    List<String> errorsDetails = new ArrayList<String>();
	        errorsDetails.add(ex.getLocalizedMessage());
	        ExceptionDetails exceptionDetails = new ExceptionDetails(dateTime.format(formatter), "Server Error", errorsDetails);
	        return new ResponseEntity(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		LocalDateTime   dateTime = LocalDateTime.now();
		List<String> errorsDetails = new ArrayList<>();
        errorsDetails.add(ex.getMessage());
        ExceptionDetails exceptionDetails = new ExceptionDetails(dateTime.format(formatter), "Resource Not Found", errorsDetails);
        return new ResponseEntity(exceptionDetails, HttpStatus.NOT_FOUND);
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LocalDateTime   dateTime = LocalDateTime.now();
		List<String> errorsDetails = new ArrayList<>();
        
		
		  ex.getBindingResult() .getFieldErrors().stream() .forEach(error ->
		  errorsDetails.add(error.getField() +" " + error.getDefaultMessage()));
		 
        ExceptionDetails exceptionDetails = new ExceptionDetails(dateTime.format(formatter), "Validation Failed ", errorsDetails);
        return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
