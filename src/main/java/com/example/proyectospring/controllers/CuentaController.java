package com.example.proyectospring.controllers;

import com.example.proyectospring.dao.ICuentaDAO;
import com.example.proyectospring.modelentity.Cliente;
import com.example.proyectospring.modelentity.Cuenta;
import com.example.proyectospring.modelentity.Producto;
import com.example.proyectospring.services.Interfaces.IClienteService;
import com.example.proyectospring.services.Interfaces.ICuentaService;
import com.example.proyectospring.services.Interfaces.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/api")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;



    @GetMapping("/cuenta")
    public List<Cuenta> getCuenta() {
        System.out.println("getCuenta");
        return cuentaService.findAll();
    }

    @GetMapping("/cuenta/cliente/{clientID}")
    public List<Cuenta> getCuentasCliente(@PathVariable Long clientID) {
        System.out.println("getCuentasCliente");
        return cuentaService.getCuentasCliente(clientID);
    }

    @PostMapping("/cuenta")
    public ResponseEntity<?> postCuenta (@Valid @RequestBody Cuenta cuenta) {
        System.out.println("postCuenta");

        Map<String,String> response = new HashMap<>();

        try {
            Cuenta savedCuenta = cuentaService.createCuenta(cuenta);
            return ResponseEntity.status(201).body(savedCuenta);
        } catch (IllegalArgumentException e) {
                // Error del cliente
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
                // Error interno del servidor
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/cuenta/{id}")
    public void deleteCuenta (@PathVariable Long id) {
        System.out.println("deleteCuenta");
        cuentaService.deleteById(id);
    }

    @GetMapping("/cuenta/{id}")
    public ResponseEntity<?> getCuentaById (@PathVariable Long id) {
        System.out.println("getCuentaById");

        Cuenta c = cuentaService.findById(id);
        System.out.println(c);

        Map<String,String> response= new HashMap<>();

        if( c==null ) {
            response.put("message","client not found");
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.status(200).body(c);
    }

    @PutMapping("/cuenta/{id}")
    public ResponseEntity<?> putCuenta (@RequestBody Cuenta cuenta, @PathVariable Long id) {
        System.out.println("putCuenta");

        Map<String,String> response = new HashMap<>();

        try {
            Cuenta savedCuenta = cuentaService.editCuenta(cuenta, id);
            return ResponseEntity.status(200).body(savedCuenta);
        } catch (IllegalArgumentException e) {
            // Error del cliente
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            // Error interno del servidor
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
