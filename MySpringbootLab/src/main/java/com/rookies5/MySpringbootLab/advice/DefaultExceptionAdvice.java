package com.rookies5.MySpringbootLab.advice;

import com.rookies5.MySpringbootLab.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    protected ProblemDetail handleException(BusinessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getHttpStatus());
        problemDetail.setTitle("Not Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss E a", Locale.KOREA)
                        .format(LocalDateTime.now()));
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleException(HttpMessageNotReadableException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", e.getMessage());
        result.put("httpStatus", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        result.put("message", "Validation failed");
        result.put("errors", errors);
        result.put("httpStatus", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorObject> handleException(Exception e) {
        ErrorObject errorObject = new ErrorObject();
        HttpStatus status = resolveHttpStatus(e);
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorObject, status);
    }

    private HttpStatus resolveHttpStatus(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return HttpStatus.METHOD_NOT_ALLOWED; 
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE; 
        } else if (e instanceof MissingServletRequestParameterException) {
            return HttpStatus.BAD_REQUEST; 
        } else if (e instanceof NoResourceFoundException) {
            return HttpStatus.NOT_FOUND; 
        }
        return HttpStatus.INTERNAL_SERVER_ERROR; 
    }
}
