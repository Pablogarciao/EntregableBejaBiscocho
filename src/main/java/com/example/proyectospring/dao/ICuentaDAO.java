package com.example.proyectospring.dao;

import com.example.proyectospring.modelentity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ICuentaDAO
        extends JpaRepository<Cuenta, Long> {
    public List<Cuenta> findByDate(Date date);
    public List<Cuenta> findByClient(Cliente client);
    public List<Cuenta> findByProduct(Producto product);
}
