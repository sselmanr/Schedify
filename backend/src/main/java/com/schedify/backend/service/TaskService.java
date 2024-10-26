package com.schedify.backend.service;

import com.schedify.backend.entity.Task;
import com.schedify.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import java.util.List;

@Service
public class TaskService{
  @Autowired
  private TaskRepository taskRepository;

  public List<Task> listarTasks(){
    return taskRepository.findAll();
  }

  public Task crearTask(Task task){
    return taskRepository.save(task);
  }

  public Task getTaskById(Long id){
    return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
  }

  public void deleteTaskById(Long id){
    taskRepository.deleteById(id);
  }

  public Task updateTaskPartial(Long id, Map<String, Object> updates){
    Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

    updates.forEach((key, value) -> {
      switch (key) {
        case "fecha":
          existingTask.setFecha((String) value);
          break;
        case "hora":
          existingTask.setHora((String) value);
          break;
      }
    });

    return taskRepository.save(existingTask);
  }

}