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

    // Es con otra tabla
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas;

    // Es con una llave foranea
    @OneToOne
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private Cliente clientID;
}
