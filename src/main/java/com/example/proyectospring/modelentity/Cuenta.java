package com.example.proyectospring.modelentity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull()
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cliente_id",
                nullable = false)
    private Cliente client;

    @ManyToOne
    @JoinColumn(name="producto_id",
                nullable = false)
    private Producto product;
}
