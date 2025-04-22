package com.unir.turnos.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "catalogo", name = "estado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Estado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado", updatable = false, nullable = false)
  private Integer idEstado;

  @Column(name = "codigo", nullable = false, unique = true)
  private Integer codigo;

  @Column(name = "nombre", nullable = false, unique = true, length = 50)
  private String nombre;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "fecha_modificacion")
  private LocalDateTime fechaModificacion;

  @PrePersist
  public void onCreate() { this.fechaCreacion = LocalDateTime.now(); }

  @PreUpdate
  public void onUpdate() { this.fechaModificacion = LocalDateTime.now(); }

}
