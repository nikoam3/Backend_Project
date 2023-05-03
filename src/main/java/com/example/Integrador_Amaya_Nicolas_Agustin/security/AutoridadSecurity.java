package com.example.Integrador_Amaya_Nicolas_Agustin.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Autoridad;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AutoridadSecurity implements GrantedAuthority{
    @Autowired
    Autoridad autoridad;
    @Override
    public String getAuthority() {
        return autoridad.getName().toString();
    }
}
