package com.example.proyectospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectospring.modelentity.Producto;

import java.util.List;
public interface IProductoDAO
        extends JpaRepository<Producto, Long>{
    public List<Producto> findByName(String name);
    public List<Producto> findByDescription(String description);
    public List<Producto> findByPrice(double Price);
    public List<Producto> findByQuantity(int Quantity);


}
