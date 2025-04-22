package com.unir.turnos.infraestructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomLogger {

  private static final Logger logger = LoggerFactory.getLogger("GlobalLogger");

  @Bean
  public static Logger getLogger() {
    return logger;
  }

  public static void info(String message) {
    logger.info(message);
  }

  public static void warn(String message) {
    logger.warn(message);
  }

  public static void error(String message) {
    logger.error(message);
  }

}

