package fap.SistemaGestionEducativa.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.util.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(RestResponse.builder()
                .success(false).code(ApiConstants.FORBIDDEN)
                .message("No tiene permisos para acceder a este recurso.")
                .data(null).build()));
    }
}
