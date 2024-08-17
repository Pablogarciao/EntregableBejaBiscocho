package com.example.proyectospring.services;

import com.example.proyectospring.dao.ICuentaDAO;
import com.example.proyectospring.modelentity.Cuenta;
import com.example.proyectospring.services.Interfaces.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements ICuentaService {
    @Autowired
    private ICuentaDAO cuentaDAO;

    @Override
    public List<Cuenta> findAll() { return cuentaDAO.findAll(); }

    @Override
    public Cuenta save(Cuenta producto) {return cuentaDAO.save(producto); }

    @Override
    public Cuenta findById(Long id) {return cuentaDAO.findById(id).orElse(null); }

    @Override
    public void deleteById(Long id) { cuentaDAO.deleteById(id); }
}
