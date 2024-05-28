package br.com.caicara.backend.model.entities.users;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user_ribeirinho")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Ribeirinho implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;
    @Column(name = "contact", nullable = false, length = 15)
    private String contact;
    @Column(name = "address",nullable = false, length = 300)
    private String address;
    @OneToOne
    @JoinColumn(name = "id_users", nullable = false)
    private Users users;
}
