package com.fabrick.exercise.controllerAdvice;

import com.fabrick.exercise.exceptions.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    private static final String URI_PREFIX = "uri=";

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<Object> handleBadRequestException(Exception e, WebRequest request) {
//        ErrorDetails errorDetails = createBasicErrorDetails(e, request, HttpStatus.BAD_REQUEST);
//
//        logMessage(String.format("Exception occurred calling url %s. Error details: {}", errorDetails.getPath()), LogLevel.WARN, errorDetails);
//
//        return getResponse(errorDetails);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = createBasicErrorDetails(e, request);
        logMessage(String.format("Exception occurred calling url %s. Error details: {}", errorDetails.getPath()), LogLevel.ERROR, errorDetails);
        return getResponse(errorDetails);
    }

    private ErrorDetails createBasicErrorDetails(Exception e, WebRequest request, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setPath(getPath(request));
        errorDetails.setStatus(status);
        errorDetails.setMessage(e.getMessage());

        return errorDetails;
    }

    private ErrorDetails createBasicErrorDetails(Exception e, WebRequest request) {
        return createBasicErrorDetails(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> getResponse(Object body, HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<Object> getResponse(Object body) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (body instanceof ErrorDetails) {
            try {
                status = HttpStatus.valueOf(((ErrorDetails) body).getStatus());
            } catch (IllegalArgumentException e) {
                log.warn(e.getMessage());
            }
        }

        return getResponse(body, status);
    }

    private String getPath(WebRequest request) {
        String path = request.getDescription(false);

        if (path.contains(URI_PREFIX)) {
            path = path.replace(URI_PREFIX, "");
        }

        return path;
    }

    private void logMessage(String message, LogLevel level, Object... args) {
        if (level == LogLevel.ERROR) {
            log.error(message, args);
        } else if (level == LogLevel.WARN) {
            log.warn(message, args);
        } else if (level == LogLevel.INFO) {
            log.info(message, args);
        } else if (level == LogLevel.DEBUG) {
            log.debug(message, args);
        } else if (level == LogLevel.TRACE) {
            log.trace(message, args);
        }
    }

}