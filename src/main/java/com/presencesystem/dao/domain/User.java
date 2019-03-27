package com.presencesystem.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = "db")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User implements Serializable {

    private static final long serialVersionUID = -4652374480448760171L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String fingerprint;

}
