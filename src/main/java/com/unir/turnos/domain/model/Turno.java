package com.unir.turnos.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "turno", name = "turno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Turno {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_turno", columnDefinition = "UUID")
  private UUID idTurno;

  @Column(name = "codigo", nullable = false, length = 6)
  private String codigo;

  @Column(name = "paciente_id", nullable = false)
  private UUID pacienteId;

  @ManyToOne
  @JoinColumn(name = "estado_id", referencedColumnName = "id_estado", foreignKey = @ForeignKey(name = ""), nullable = false)
  private Estado estado;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "fecha_finalizacion", nullable = false)
  private LocalDateTime fechaFinalizacion;

  @PrePersist
  public void onCreate() {
    this.fechaCreacion = LocalDateTime.now();
  }

}
