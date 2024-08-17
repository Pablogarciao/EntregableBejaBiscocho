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


//    @ManyToMany ()
//    @JoinTable(
//            name="chef_postre",
//            joinColumns = @JoinColumn(name="chef_id"),
//            inverseJoinColumns = @JoinColumn(name="postre_id")
//    )
//    private List<Postre> postres;

}
