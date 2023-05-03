package com.example.Integrador_Amaya_Nicolas_Agustin.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@AllArgsConstructor
public class UsuarioSecurity implements UserDetails {
    @Autowired
    Usuario usuario;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getAutoridades().stream().map(AutoridadSecurity::new).toList();
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
