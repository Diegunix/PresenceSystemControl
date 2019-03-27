package com.presencesystem.dao.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timetable", schema = "db")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Timetable implements Serializable {

    private static final long serialVersionUID = 6250491396396124297L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column
    @NotNull
    private LocalDateTime actionDate;

    @Column(nullable = false)
    private Boolean action;

}
