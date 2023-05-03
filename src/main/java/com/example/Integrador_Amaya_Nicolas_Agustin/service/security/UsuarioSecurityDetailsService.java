package com.example.Integrador_Amaya_Nicolas_Agustin.service.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.repository.security.IUsuarioRepository;
import com.example.Integrador_Amaya_Nicolas_Agustin.security.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSecurityDetailsService implements UserDetailsService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionUsuario = this.iUsuarioRepository.findByUsername(username);
        if (optionUsuario.isPresent()){
            return new UsuarioSecurity(optionUsuario.get());
        }
        return (UserDetails) new UsernameNotFoundException ("Usuario no existe" + username);
    }
}
