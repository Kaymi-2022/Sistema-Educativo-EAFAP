package fap.SistemaGestionEducativa.service.security;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciales inválidas."));
        if (!"Y".equalsIgnoreCase(usuario.getEstado())) {
            throw new UsernameNotFoundException("El usuario se encuentra inactivo.");
        }
        var authorities = usuario.getUsuarioRoles().stream()
                .filter(ur -> ur.getRol() != null && "Y".equalsIgnoreCase(ur.getRol().getEstado()))
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRol().getNombreRol().toUpperCase(Locale.ROOT)))
                .toList();
        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .disabled(false)
                .build();
    }
}
