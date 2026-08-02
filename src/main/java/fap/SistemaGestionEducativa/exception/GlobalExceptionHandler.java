package fap.SistemaGestionEducativa.exception;

import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.util.ApiConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .code(ApiConstants.NOT_FOUND)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicate(DuplicateResourceException ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .code(ApiConstants.CONFLICT)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusiness(BusinessException ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .code(ApiConstants.BAD_REQUEST)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex){

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .code(ApiConstants.BAD_REQUEST)
                .message("Error de validación en los datos de entrada.")
                .data(errors)
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .code(ApiConstants.INTERNAL_SERVER_ERROR)
                .message("Ocurrió un error inesperado en el servidor.")
                .data(null)
                .timestamp(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }

}