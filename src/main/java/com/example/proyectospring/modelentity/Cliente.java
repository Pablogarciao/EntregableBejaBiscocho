package com.example.proyectospring.modelentity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.util.List;
@Entity
@Table(name="clientes")
@Data()
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String address;

    @Column()
    @Email()
    private String email;

    @Column()
    private String phone;
}
