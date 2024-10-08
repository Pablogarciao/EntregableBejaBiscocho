package com.example.proyectospring.services.Interfaces;

import com.example.proyectospring.modelentity.Cuenta;

import java.util.List;

public interface ICuentaService {
    public List<Cuenta> findAll();
    public Cuenta save(Cuenta cuenta);
    public Cuenta findById(Long id);
    public void deleteById(Long id);

    public List<Cuenta> getCuentasCliente(Long clientID);
    public Cuenta createCuenta(Cuenta cuenta) throws IllegalArgumentException;
    public Cuenta editCuenta(Cuenta cuenta, Long id) throws IllegalArgumentException;
}
