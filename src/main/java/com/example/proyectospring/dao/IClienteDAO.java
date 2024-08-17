package com.example.proyectospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectospring.modelentity.Cliente;

import java.util.List;

public interface IClienteDAO
        extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByName(String name);
    public List<Cliente> findByAddress(String address);
    public List<Cliente> findByEmail(String email);
    public List<Cliente> findByPhone(String phone);
}
