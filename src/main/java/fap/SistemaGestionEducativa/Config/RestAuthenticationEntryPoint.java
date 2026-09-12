package fap.SistemaGestionEducativa.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.util.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(RestResponse.builder()
                .success(false).code(ApiConstants.UNAUTHORIZED).message("Autenticación requerida.")
                .data(null).build()));
    }
}
