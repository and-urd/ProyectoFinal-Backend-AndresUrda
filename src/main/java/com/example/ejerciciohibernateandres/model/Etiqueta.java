package com.example.ejerciciohibernateandres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="etiquetas")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String creador;

    @Column(name="createdat")
    private LocalDate createdAt;

    @Column(name="updatedat")
    private LocalDate updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.EAGER)
    private List<Experto> expertos = new ArrayList<>();


    // constructores
    public Etiqueta() {
    }

    public Etiqueta(String nombre, String creador, LocalDate createAt, LocalDate updatedAt) {
        this.nombre = nombre;
        this.creador = creador;
        this.createdAt = createAt;
        this.updatedAt = updatedAt;
    }

    // Getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Experto> getExpertos() {
        return expertos;
    }

    public void setExpertos(List<Experto> expertos) {
        this.expertos = expertos;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
// método toString


    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creador='" + creador + '\'' +
                ", createAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", num_expertos=" + expertos.size() +
                '}';
    }
}
