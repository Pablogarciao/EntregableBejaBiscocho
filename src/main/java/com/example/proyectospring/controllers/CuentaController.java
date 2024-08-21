package com.example.proyectospring.controllers;

import com.example.proyectospring.modelentity.Cliente;
import com.example.proyectospring.modelentity.Cuenta;
import com.example.proyectospring.modelentity.Producto;
import com.example.proyectospring.services.ClienteService;
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

    @PostMapping("/cuenta")
    public ResponseEntity<?> postCuenta (@Valid @RequestBody Cuenta cuenta) {
        System.out.println("postCuenta");

        Map<String,String> response = new HashMap<>();

        try {
            // Verificar que cliente y producto existan
            Cliente cliente = clienteService.findById(cuenta.getClient().getId());
            if (cliente == null) {
                response.put("message", "Client not found");
                return ResponseEntity.status(404).body(response);
            }

            Producto producto = productoService.findById(cuenta.getProduct().getId());
            if (producto == null) {
                response.put("message", "Product not found");
                return ResponseEntity.status(404).body(response);
            }

            // Restar stock
            int productoQuantity = producto.getQuantity();
            int cuentaQuantity = cuenta.getQuantity();

            if (productoQuantity < cuentaQuantity) {
                response.put("message", "Not enough stock");
                return ResponseEntity.status(404).body(response);
            }

            producto.setQuantity(productoQuantity - cuentaQuantity);
            productoService.save(producto);

            // Colocar la cuenta con la fecha actual
            cuenta.setDate(new Date());

            // Guardar la cuenta
            cuentaService.save(cuenta);
        } catch (Exception e){
            response.put("message",e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(201).body(cuenta);
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
            Cuenta c = cuentaService.findById(id);
            if (c == null) {
                response.put("message", "Cuenta not found");
                return ResponseEntity.status(404).body(response);
            }

            // Verificar que cliente y producto existan
            Cliente cliente = clienteService.findById(cuenta.getClient().getId());
            if (cliente == null) {
                response.put("message", "Client not found");
                return ResponseEntity.status(404).body(response);
            }

            Producto productoNuevo = productoService.findById(cuenta.getProduct().getId());
            if (productoNuevo == null) {
                response.put("message", "Product not found");
                return ResponseEntity.status(404).body(response);
            }

            Producto productoAntiguo = c.getProduct();

            // Verificar si son el mismo producto
            int productoNQuantity = productoNuevo.getQuantity();
            int productoAQuantity = productoAntiguo.getQuantity();
            int cuentaAQuantity = c.getQuantity();
            int cuentaNQuantity = cuenta.getQuantity();

            // Verificar si son el mismo producto
            if (productoAntiguo.getId() == productoNuevo.getId()) {
                int diferencia = cuentaAQuantity - cuentaNQuantity;

                productoNuevo.setQuantity(productoNQuantity + diferencia);

                if (productoNuevo.getQuantity() < cuentaNQuantity) {
                    response.put("message", "Not enough stock");
                    return ResponseEntity.status(404).body(response);
                }

                productoService.save(productoNuevo);

            } else {
                // Devolver el stock
                productoAntiguo.setQuantity(productoAQuantity + cuentaAQuantity);

                // Restar stock
                if (productoNQuantity < cuentaNQuantity) {
                    response.put("message", "Not enough stock");
                    return ResponseEntity.status(404).body(response);
                }

                productoNuevo.setQuantity(productoNQuantity - cuentaNQuantity);
                productoService.save(productoNuevo);
                productoService.save(productoAntiguo);
            }

            // Colocar la cuenta con la fecha actual
            c.setDate(new Date());

            // Guardar la cuenta
            c.setQuantity(cuenta.getQuantity());
            c.setProduct(cuenta.getProduct());
            c.setClient(cuenta.getClient());

            cuentaService.save(c);

            return ResponseEntity.status(201).body(c);
        } catch (Exception e){
            response.put("message",e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
