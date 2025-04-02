package com.example.customsort.controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * GlobalExceptionHandler is a class that handles exceptions globally for the application.
 * It uses Spring's @RestControllerAdvice to handle exceptions thrown by any controller.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

  /** Logger for GlobalExceptionHandler */
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handles MethodArgumentNotValidException and returns a detailed error response.
   *
   * @param ex the MethodArgumentNotValidException
   * @return a ResponseEntity with validation error details
   */
  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
      org.springframework.web.bind.MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    // Extract each field-specific validation error and put it into the response map
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    // Return with 400 Bad Request
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles HttpMessageNotReadableException and returns a meaningful error response.
   *
   * @param ex the HttpMessageNotReadableException
   * @return a ResponseEntity with error details
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    Map<String, String> response = new HashMap<>();

    response.put("error", "Malformed Request body");
    response.put("message", ex.getMessage());

    logger.error("HttpMessageNotReadableException: {}", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles all uncaught exceptions.
   *
   * <p><strong>Note:</strong> This is a generic fallback and should be used carefully. For
   * production systems, specific exception types should be handled individually.
   *
   * @param ex The exception thrown anywhere in the application.
   * @return A generic error message map with HTTP 500 Internal Server Error status.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> response = new HashMap<>();

    // Provide a generic error response with exception message
    response.put("error", ex.getMessage());
    response.put("message", "An unexpected error occurred");
    logger.error("Generic Exception: {}", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
