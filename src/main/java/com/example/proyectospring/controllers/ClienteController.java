package com.example.proyectospring.controllers;

import com.example.proyectospring.modelentity.Cliente;
import com.example.proyectospring.services.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController()
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private IClienteService clientesService;
    @GetMapping("/cliente")
    public List<Cliente> getCliente() {
        System.out.println("getCliente");
        return clientesService.findAll();
    }
    @PostMapping("/cliente")
    public ResponseEntity<?> postCliente (@Valid @RequestBody Cliente cliente) {
        System.out.println("postCliente");

        Map<String,String> response= new HashMap<>();

        try{
            clientesService.save(cliente);
        } catch (Exception e){
            response.put("message",e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(201).body(cliente);
    }
    
    @DeleteMapping("/cliente/{id}")
    public void deleteCliente
            (@PathVariable Long id) {
        clientesService.deleteById(id);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getClienteById (@PathVariable Long id) {
        System.out.println("getClienteById");

        Cliente c = clientesService.findById(id);
        System.out.println(c);

        Map<String,String> response= new HashMap<>();

        if( c==null ) {
            response.put("message","client not found");
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.status(200).body(c);
    }

    @PutMapping("/cliente/{id}")
    public Cliente putCliente
            (@RequestBody Cliente Cliente, @PathVariable Long id) {
        Cliente c= clientesService.findById(id);
        if(c==null){
            throw new RuntimeException("Cliente no encontrado");
        }
        c.setName(Cliente.getName());
        c.setEmail(Cliente.getEmail());
        c.setPhone(Cliente.getPhone());
        c.setAddress(Cliente.getAddress());


        return clientesService.save(c);
    }
}
