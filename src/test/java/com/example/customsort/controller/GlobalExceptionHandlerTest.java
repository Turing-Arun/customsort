package com.example.customsort.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  /*
   * Tests the {@code handleGenericException} method of the {@code GlobalExceptionHandler} class.
   * If an exception is thrown, it results in a 500 Internal Server Error
   * with a generic error message.
   * This is a fallback for all uncaught exceptions.
   */
  @Test
  void testHandleGenericException() {
    Exception ex = new RuntimeException("Something went wrong");

    ResponseEntity<Map<String, String>> response = handler.handleGenericException(ex);

    // Validate status and body
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().get("error").contains("Something went wrong"));
  }

  /*
   * Tests the {@code handleHttpMessageNotReadableException} method of the {@code GlobalExceptionHandler} class.
   * If message is not readable it results in bad request
   */
  @Test
  void testHandleHttpMessageNotReadableException() {
    HttpMessageNotReadableException ex =
        new HttpMessageNotReadableException("Invalid JSON", new Throwable());

    ResponseEntity<Map<String, String>> response =
        handler.handleHttpMessageNotReadableException(ex);

    // Validate status and body
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    String expectedErrMessage = "Malformed Request body";
    assertTrue(response.getBody().get("error").toString().contains(expectedErrMessage));
  }

  @Test
  @DisplayName("Should return 400 BAD_REQUEST with field errors")
  void testHandleValidationException() {
    // Mock binding result with two field errors
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors())
        .thenReturn(
            List.of(
                new FieldError("objectName", "field1", "The integers list cannot be null"),
                new FieldError("objectName", "field2", "The integers list cannot be empty")));

    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

    ResponseEntity<Map<String, String>> response =
        handler.handleMethodArgumentNotValidException(ex);

    // Validate status and body
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("The integers list cannot be null", response.getBody().get("field1"));
    assertEquals("The integers list cannot be empty", response.getBody().get("field2"));
  }
}
