package com.rubenmartin.calenderyback.rol.infrastrcture.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@Data
@NoArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id_rol;

    @Column(nullable = false, name = "rol_name")
    String rolName;
}
