package fap.SistemaGestionEducativa.util;

import fap.SistemaGestionEducativa.dto.response.RestResponse;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> RestResponse<T> success(String code, String message, T data) {

        return RestResponse.<T>builder()
                .success(true)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> RestResponse<T> error(String code, String message) {

        return RestResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }
    }
