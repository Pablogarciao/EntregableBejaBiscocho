package com.example.proyectospring.controllers;

import com.example.proyectospring.modelentity.Producto;
import com.example.proyectospring.services.Interfaces.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController()
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IProductoService productosService;

    @GetMapping("/producto")
    public List<Producto> getProductos() {
        System.out.println("getProductos");
        return productosService.findAll();
    }

    @PostMapping("/producto")
    public ResponseEntity<?> postProducto (@Valid @RequestBody Producto producto) {
        System.out.println("postCliente");

        Map<String,String> response= new HashMap<>();

        try{
            productosService.save(producto);
        } catch (Exception e){
            response.put("message",e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(201).body(producto);
    }

    @DeleteMapping("/producto/{id}")
    public void deleteCliente (@PathVariable Long id) {
        System.out.println("deleteCliente");
        productosService.deleteById(id);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> getProductoById (@PathVariable Long id) {
        System.out.println("getProductoById");

        Producto p = productosService.findById(id);
        System.out.println(p);

        Map<String,String> response= new HashMap<>();

        if( p==null ) {
            response.put("message","product not found");
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.status(200).body(p);
    }

    @PutMapping("/producto/{id}")
    public Producto putProducto (@RequestBody Producto Producto, @PathVariable Long id) {
        System.out.println("putProducto");

        Producto p= productosService.findById(id);

        if(p==null){
            throw new RuntimeException("Producto no encontrado");
        }

        p.setName(Producto.getName());
        p.setDescription(Producto.getDescription());
        p.setPrice(Producto.getPrice());
        p.setQuantity(Producto.getQuantity());

        return productosService.save(p);
    }
}
