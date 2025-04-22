package com.unir.turnos.application.util;

import com.unir.turnos.infraestructure.config.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CodigoTurnoServicio {

  private final CondicionServicio condicionServicio;
  private final Map<String, AtomicInteger> contadores = new HashMap<>();

  @Scheduled(cron = "0 0 0 * * *")
  private void reinciarContadores() {
    contadores.clear();
    CustomLogger.warn("CONTADORES DE TURNOS REINICIADOS.");
  }

  public synchronized String generarCodigoTurno(Integer condicion) {
    String letras = condicionServicio.getCondicion(condicion).getPrioritario() ? "PE" : "E";
    AtomicInteger contador = contadores.computeIfAbsent(letras, x -> new AtomicInteger(0));
    int numero = contador.incrementAndGet();
    return letras + numero;
  }

}
