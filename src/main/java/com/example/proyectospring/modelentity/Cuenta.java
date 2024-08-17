package com.example.proyectospring.modelentity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="cuenta")
@Data()
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Date date;

    @ManyToOne
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private Cliente client;
}
