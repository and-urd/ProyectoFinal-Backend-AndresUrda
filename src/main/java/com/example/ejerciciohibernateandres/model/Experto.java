package com.example.ejerciciohibernateandres.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expertos")
public class Experto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // created_at
    @Column(name="created_at")
    private LocalDate createdAt;

    // updated_at
    @Column(name="updated_at")
    private LocalDate updatedAt;

    // estado_motivo
    @Column(name="estado_motivo")
    private String estadoMotivo;

    // disponibilidad
    private String disponibilidad;

    private String modalidad;

    // autonomo Boolean
    private Boolean autonomo;

    private String telefono;

    private String email;


    @Column(name="contacto_ciudad")
    private String contactoCiudad;

    @Column(name="contacto_linkedin")
    private String contactoLinkedin;

    @Column(name="condiciones_porcentaje")
    private String condicionesPorcentaje;

    @Column(name="condiciones_precio_hora")
    private String condicionesPrecioHora;

    private Integer puntuacion;

    private String nif;

    @Column(name="credenciales_correo")
    private String credencialesCorreo;

    @Column(name="credenciales_correo_password")
    private String credencialesCorreoPassword;

    @Column(name="credenciales_zoom")
    private String credencialesZoom;

    @Column(name="credenciales_zoom_password")
    private String credencialesZoomPassword;

    @Column(name="fichero_foto")
    private String ficheroFoto;

    @Column(name="fichero_cv")
    private String ficheroCV;

    private String observaciones;

    private String origen;








    private String estado;

    @ManyToMany
    @JoinTable(
            name="experto_etiqueta",
            joinColumns = {@JoinColumn(name="experto_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="etiqueta_id", referencedColumnName = "id")}
    )
    private List<Etiqueta> etiquetas = new ArrayList<>();


    // constructores
    public Experto() {
    }

    public Experto(String nombre, LocalDate createdAt, LocalDate updatedAt, String estadoMotivo, String disponibilidad, String modalidad, Boolean autonomo, String telefono, String email, String contactoCiudad, String contactoLinkedin, String condicionesPorcentaje, String condicionesPrecioHora, Integer puntuacion, String nif, String credencialesCorreo, String credencialesCorreoPassword, String credencialesZoom, String credencialesZoomPassword, String ficheroFoto, String ficheroCV, String observaciones, String origen, String estado, List<Etiqueta> etiquetas) {
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.estadoMotivo = estadoMotivo;
        this.disponibilidad = disponibilidad;
        this.modalidad = modalidad;
        this.autonomo = autonomo;
        this.telefono = telefono;
        this.email = email;
        this.contactoCiudad = contactoCiudad;
        this.contactoLinkedin = contactoLinkedin;
        this.condicionesPorcentaje = condicionesPorcentaje;
        this.condicionesPrecioHora = condicionesPrecioHora;
        this.puntuacion = puntuacion;
        this.nif = nif;
        this.credencialesCorreo = credencialesCorreo;
        this.credencialesCorreoPassword = credencialesCorreoPassword;
        this.credencialesZoom = credencialesZoom;
        this.credencialesZoomPassword = credencialesZoomPassword;
        this.ficheroFoto = ficheroFoto;
        this.ficheroCV = ficheroCV;
        this.observaciones = observaciones;
        this.origen = origen;
        this.estado = estado;
        this.etiquetas = etiquetas;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
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

    public String getEstadoMotivo() {
        return estadoMotivo;
    }

    public void setEstadoMotivo(String estadoMotivo) {
        this.estadoMotivo = estadoMotivo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean getAutonomo() {
        return autonomo;
    }

    public void setAutonomo(Boolean autonomo) {
        this.autonomo = autonomo;
    }

    public String getContactoCiudad() {
        return contactoCiudad;
    }

    public void setContactoCiudad(String contactoCiudad) {
        this.contactoCiudad = contactoCiudad;
    }

    public String getContactoLinkedin() {
        return contactoLinkedin;
    }

    public void setContactoLinkedin(String contactoLinkedin) {
        this.contactoLinkedin = contactoLinkedin;
    }

    public String getCondicionesPorcentaje() {
        return condicionesPorcentaje;
    }

    public void setCondicionesPorcentaje(String condicionesPorcentaje) {
        this.condicionesPorcentaje = condicionesPorcentaje;
    }

    public String getCondicionesPrecioHora() {
        return condicionesPrecioHora;
    }

    public void setCondicionesPrecioHora(String condicionesPrecioHora) {
        this.condicionesPrecioHora = condicionesPrecioHora;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCredencialesCorreo() {
        return credencialesCorreo;
    }

    public void setCredencialesCorreo(String credencialesCorreo) {
        this.credencialesCorreo = credencialesCorreo;
    }

    public String getCredencialesCorreoPassword() {
        return credencialesCorreoPassword;
    }

    public void setCredencialesCorreoPassword(String credencialesCorreoPassword) {
        this.credencialesCorreoPassword = credencialesCorreoPassword;
    }

    public String getCredencialesZoom() {
        return credencialesZoom;
    }

    public void setCredencialesZoom(String credencialesZoom) {
        this.credencialesZoom = credencialesZoom;
    }

    public String getCredencialesZoomPassword() {
        return credencialesZoomPassword;
    }

    public void setCredencialesZoomPassword(String credencialesZoomPassword) {
        this.credencialesZoomPassword = credencialesZoomPassword;
    }

    public String getFicheroFoto() {
        return ficheroFoto;
    }

    public void setFicheroFoto(String ficheroFoto) {
        this.ficheroFoto = ficheroFoto;
    }

    public String getFicheroCV() {
        return ficheroCV;
    }

    public void setFicheroCV(String ficheroCV) {
        this.ficheroCV = ficheroCV;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    // método toString

    @Override
    public String toString() {
        return "Experto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", estadoMotivo='" + estadoMotivo + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", autonomo=" + autonomo +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", contactoCiudad='" + contactoCiudad + '\'' +
                ", contactoLinkedin='" + contactoLinkedin + '\'' +
                ", condicionesPorcentaje='" + condicionesPorcentaje + '\'' +
                ", condicionesPrecioHora='" + condicionesPrecioHora + '\'' +
                ", puntuacion=" + puntuacion +
                ", nif='" + nif + '\'' +
                ", credencialesCorreo='" + credencialesCorreo + '\'' +
                ", credencialesCorreoPassword='" + credencialesCorreoPassword + '\'' +
                ", credencialesZoom='" + credencialesZoom + '\'' +
                ", credencialesZoomPassword='" + credencialesZoomPassword + '\'' +
                ", ficheroFoto='" + ficheroFoto + '\'' +
                ", ficheroCV='" + ficheroCV + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", origen='" + origen + '\'' +
                ", estado='" + estado + '\'' +
                ", etiquetas=" + etiquetas.size() +
                '}';
    }
}
