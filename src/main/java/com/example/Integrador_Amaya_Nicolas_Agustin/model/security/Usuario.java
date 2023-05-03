package com.example.Integrador_Amaya_Nicolas_Agustin.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_autoridad",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "autoridad_id", referencedColumnName = "id"))
    private List<Autoridad> autoridades;



    public Usuario(String name, String username, String email, String password,  List<Autoridad> autoridades) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.autoridades = autoridades;
    }

}