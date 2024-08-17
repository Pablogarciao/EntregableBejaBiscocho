package com.example.proyectospring.controllers;

import com.example.proyectospring.modelentity.Cuenta;
import com.example.proyectospring.services.Interfaces.ICuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/cuenta")
    public List<Cuenta> getCuenta() {
        System.out.println("getCuenta");
        return cuentaService.findAll();
    }

    @PostMapping("/cuenta")
    public ResponseEntity<?> postCuenta (@Valid @RequestBody Cuenta cuenta) {
        System.out.println("postCuenta");

        Map<String,String> response= new HashMap<>();

        try{
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
    public Cuenta putCuenta (@RequestBody Cuenta Cuenta, @PathVariable Long id) {
        System.out.println("putCuenta");

        Cuenta c= cuentaService.findById(id);

        if(c==null){
            throw new RuntimeException("Cuenta no encontrada");
        }

        c.setDate(Cuenta.getDate());
        c.setClient(Cuenta.getClient());
        c.setProduct(Cuenta.getProduct());

        return cuentaService.save(c);
    }
}
