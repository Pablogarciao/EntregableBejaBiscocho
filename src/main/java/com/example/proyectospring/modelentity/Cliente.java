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


//    @ManyToMany ()
//    @JoinTable(
//            name="chef_postre",
//            joinColumns = @JoinColumn(name="chef_id"),
//            inverseJoinColumns = @JoinColumn(name="postre_id")
//    )
//    private List<Postre> postres;

}
