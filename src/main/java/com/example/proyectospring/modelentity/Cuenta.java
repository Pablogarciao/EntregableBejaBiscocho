package com.example.proyectospring.modelentity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="cuentas")
@Data()
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Date date;

    @Column()
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cliente_id",
                referencedColumnName = "id",
                nullable = false)
    private Cliente client;

    @ManyToOne
    @JoinColumn(name="producto_id",
                referencedColumnName = "id",
                nullable = false)
    private Producto product;
}
