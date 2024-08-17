package com.example.proyectospring.modelentity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="productos")
@Data()
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private double price;

    @Column()
    private int quantity;
}
