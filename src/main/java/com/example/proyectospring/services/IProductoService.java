package com.example.proyectospring.services;

import com.example.proyectospring.modelentity.Producto;
import java.util.*;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save(Producto producto);

    public Producto findById(Long id);

    public void deleteById(Long id);
}
