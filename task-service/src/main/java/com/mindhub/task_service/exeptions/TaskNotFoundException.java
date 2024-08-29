package com.mindhub.task_service.exeptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id) {
        super("Tarea con ID " + id + " no encontrada");
    }
}
