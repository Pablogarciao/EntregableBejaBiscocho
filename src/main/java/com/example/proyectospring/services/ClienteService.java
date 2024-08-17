package com.example.proyectospring.services;

import com.example.proyectospring.dao.IClienteDAO;
import com.example.proyectospring.modelentity.Cliente;
import com.example.proyectospring.services.Interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public List<Cliente> findAll() { return clienteDAO.findAll(); }

    @Override
    public Cliente save(Cliente cliente) {return clienteDAO.save(cliente); }

    @Override
    public Cliente findById(Long id) {return clienteDAO.findById(id).orElse(null); }

    @Override
    public void deleteById(Long id) { clienteDAO.deleteById(id); }
}
