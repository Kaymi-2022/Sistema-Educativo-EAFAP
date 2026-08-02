package fap.SistemaGestionEducativa.util;

import fap.SistemaGestionEducativa.dto.response.ApiResponse;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(String code,String message,T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message) {

        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }
    }
