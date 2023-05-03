package com.example.Integrador_Amaya_Nicolas_Agustin.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    Boolean save(T t);
    Boolean delete(Long id);
    Boolean update(T t);
}
