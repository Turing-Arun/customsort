package com.example.customsort.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a controller advice class that handles exceptions thrown by any
 * controller method. It provides a centralized exception handling across all @RequestMapping
 * methods.
 *
 * <p>Currently, it handles the following exceptions:
 *
 * <ul>
 *   <li>IllegalArgumentException - returns a ResponseEntity with a BAD_REQUEST status and the
 *       exception message as the body
 * </ul>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * throw new IllegalArgumentException("Invalid argument");
 * }</pre>
 *
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 */
public class GlobalExceptionHandler {

  /**
   * Handles IllegalArgumentException exceptions thrown by any controller method.
   *
   * @param ex the IllegalArgumentException thrown
   * @return a ResponseEntity containing the exception message and a BAD_REQUEST (400) status code
   *     <p><b>Good Scenario:</b> When an IllegalArgumentException is thrown due to invalid input
   *     parameters, this handler will catch the exception and return a clear error message to the
   *     client with a 400 status code.
   *     <p><b>Bad Scenario:</b> If the exception message is not user-friendly or does not provide
   *     enough information, the client may not understand the cause of the error, leading to
   *     confusion and difficulty in resolving the issue.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
