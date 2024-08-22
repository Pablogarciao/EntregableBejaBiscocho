package com.example.proyectospring.dao;

import com.example.proyectospring.modelentity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ICuentaDAO
        extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByDate(Date date);

    @Query("SELECT c FROM Cuenta c WHERE c.client.id = :clientID")
    List<Cuenta> findCuentaByClient(@Param("clientID") Long clientID);

    List<Cuenta> findByProduct(Producto product);
}
