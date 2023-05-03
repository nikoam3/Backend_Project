package com.example.Integrador_Amaya_Nicolas_Agustin.service.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Autoridad;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Usuario;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.UsuarioRoles;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.security.IAutoridadRepository;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.security.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    //private UserRepository userRepository;

    @Autowired
    IUsuarioRepository iUsuarioRepository;
    @Autowired
    IAutoridadRepository iAutoridadRepository;
    //public DataLoader(UserRepository userRepository) {
    //   this.userRepository = userRepository;
    //}

//    public void run(ApplicationArguments args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode("123456");
//        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
//        String hashedPassword2 = passwordEncoder2.encode("password");
//        iUsuarioRepository.save(new Usuario("Nicolas", "nico", "nico@digital.com", hashedPassword, UsuarioRoles.ADMIN));
//        iUsuarioRepository.save(new Usuario("Agustin", "agustin", "agustin@digital.com", hashedPassword2, UsuarioRoles.USER));
//    }

    public void run(String... args) throws Exception {

        if (this.iAutoridadRepository.count() == 0) {
            this.iAutoridadRepository.saveAll(List.of(
                    new Autoridad(UsuarioRoles.ADMIN),
                    new Autoridad(UsuarioRoles.USER)
            ));
        }

        if (this.iUsuarioRepository.count() == 0) {
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            this.iUsuarioRepository.saveAll(List.of(
                            new Usuario("Nicolas", "admin", "nico_admin@digital.com", encoders.encode("123456"), List.of(this.iAutoridadRepository.findByName(UsuarioRoles.ADMIN).get())),
                            new Usuario("Agustin", "user", "agus_user@digital.com", encoders.encode("123456"), List.of(this.iAutoridadRepository.findByName(UsuarioRoles.USER).get())))
            );
        }
    }
}
