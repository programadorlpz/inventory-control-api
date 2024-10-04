package com.gradle.exception;

import java.time.LocalDateTime;

/**
 * Clase que representa los detalles de un error.
 * Utilizada para enviar información estructurada sobre los errores al cliente.
 */
public class ErrorDetails {

    private LocalDateTime timestamp; 		// Fecha y hora del error
    private String message; 				// Mensaje del error
    private String details; 				// Detalles adicionales (opcional, como la URL o descripción de la petición)

    // Constructor
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters y Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
