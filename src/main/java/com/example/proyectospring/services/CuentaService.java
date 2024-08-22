package com.example.proyectospring.services;

import com.example.proyectospring.dao.ICuentaDAO;
import com.example.proyectospring.modelentity.Cliente;
import com.example.proyectospring.modelentity.Cuenta;
import com.example.proyectospring.modelentity.Producto;
import com.example.proyectospring.services.Interfaces.IClienteService;
import com.example.proyectospring.services.Interfaces.ICuentaService;
import com.example.proyectospring.services.Interfaces.IProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CuentaService implements ICuentaService {
    @Autowired
    private ICuentaDAO cuentaDAO;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;

    @Override
    public List<Cuenta> findAll() { return cuentaDAO.findAll(); }

    @Override
    public Cuenta save(Cuenta producto) {return cuentaDAO.save(producto); }

    @Override
    public Cuenta findById(Long id) {return cuentaDAO.findById(id).orElse(null); }

    @Override
    public void deleteById(Long id) { cuentaDAO.deleteById(id); }

    @Override
    public List<Cuenta> getCuentasCliente(Long clientID) {
        return cuentaDAO.findCuentaByClient(clientID);
    }

    @Transactional
    public Cuenta createCuenta(Cuenta cuenta) throws IllegalArgumentException {
            // Verificar que el cliente exista
        if (cuenta.getClient() == null) {
            throw new IllegalArgumentException("Client not sended");
        }

        Cliente cliente = clienteService.findById(cuenta.getClient().getId());
        if (cliente == null) {
            throw new IllegalArgumentException("Client not found");
        }

            // Verificar que el producto exista
        if (cuenta.getProduct() == null) {
            throw new IllegalArgumentException("Product not sended");
        }

        Producto producto = productoService.findById(cuenta.getProduct().getId());
        if (producto == null) {
            throw new IllegalArgumentException("Product not found");
        }

            // Guardar cuenta con fecha actual
        cuenta.setDate(new Date());

            // Stock
        int productoQuantity = producto.getQuantity();
        int cuentaQuantity = cuenta.getQuantity();

        // Verificar que la cantidad sea mayor que 0
        if (cuentaQuantity <= 0) {
            throw new IllegalArgumentException("Quantity <= 0");
        }

        // Verificar que halla stock suficiente
        if (productoQuantity < cuentaQuantity) {
            throw new IllegalArgumentException("Not enough stock");
        }

        // Restar Stock
        producto.setQuantity(productoQuantity - cuentaQuantity);
        productoService.save(producto);

            // Guardar la cuenta
        return cuentaDAO.save(cuenta);
    }

    @Transactional
    public Cuenta editCuenta(Cuenta cuenta, Long id) throws IllegalArgumentException {
            // Verificar que la cuenta exista
        Cuenta c = cuentaDAO.findById(id).orElse(null);
        if (c == null) {
            throw new IllegalArgumentException("Cuenta not found");
        }

            // Verificar que cliente exista
        if (cuenta.getClient() == null) {
            throw new IllegalArgumentException("Client not sended");
        }

        Cliente cliente = clienteService.findById(cuenta.getClient().getId());
        if (cliente == null) {
            throw new IllegalArgumentException("Client not found");
        }

            // Verificar que producto exista
        if (cuenta.getProduct() == null) {
            throw new IllegalArgumentException("Product not sended");
        }

        Producto productoNuevo = productoService.findById(cuenta.getProduct().getId());
        if (productoNuevo == null) {
            throw new IllegalArgumentException("Product not found");
        }

            // Obtener info del producto de la cuenta anterior
        Producto productoAntiguo = c.getProduct();

            // Colocar la cuenta con la fecha actual
        c.setDate(new Date());

            // Variables de cantidad
        int productoNQuantity = productoNuevo.getQuantity();
        int productoAQuantity = productoAntiguo.getQuantity();
        int cuentaAQuantity = c.getQuantity();
        int cuentaNQuantity = cuenta.getQuantity();

            // Verificar si son el mismo producto
        if (productoAntiguo.getId().equals(productoNuevo.getId())) {
            int diferencia = cuentaAQuantity - cuentaNQuantity;

            // Devolver la diferencia de la cantidad
            productoNuevo.setQuantity(productoNQuantity + diferencia);

            if (productoNuevo.getQuantity() < cuentaNQuantity) {
                throw new IllegalArgumentException("Not enough stock");
            }

            productoService.save(productoNuevo);

        } else {
            // Devolver el stock del producto antiguo
            productoAntiguo.setQuantity(productoAQuantity + cuentaAQuantity);

            // Verificar stock del nuevo producto
            if (productoNQuantity < cuentaNQuantity) {
                throw new IllegalArgumentException("Not enough stock");
            }

            // Restar stock del nuevo producto
            productoNuevo.setQuantity(productoNQuantity - cuentaNQuantity);

                // Guardar ambos productos
            productoService.save(productoNuevo);
            productoService.save(productoAntiguo);
        }

            // Actualizar la cuenta con los nuevos valores
        c.setQuantity(cuenta.getQuantity());
        c.setProduct(cuenta.getProduct());
        c.setClient(cuenta.getClient());

            // Guardar la cuenta actualizada
        return cuentaDAO.save(c);
    }

}
