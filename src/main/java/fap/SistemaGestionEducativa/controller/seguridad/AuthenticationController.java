package fap.SistemaGestionEducativa.controller.seguridad;

import fap.SistemaGestionEducativa.dto.request.academico.LoginRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.LoginResponse;
import fap.SistemaGestionEducativa.service.security.JwtService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public RestResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replaceFirst("^ROLE_", ""))
                .map(role -> role.toUpperCase(Locale.ROOT)).toList();
        LoginResponse data = LoginResponse.builder().token(jwtService.generateToken(userDetails))
                .username(userDetails.getUsername()).roles(roles).build();
        return ResponseBuilder.success(ApiConstants.SUCCESS, "Autenticación exitosa.", data);
    }
}
