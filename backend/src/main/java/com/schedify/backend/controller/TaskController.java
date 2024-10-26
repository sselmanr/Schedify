package com.schedify.backend.controller;

import com.schedify.backend.entity.Task;
import com.schedify.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> listarTasks() {
        return taskService.listarTasks();
    }
    @GetMapping("/{id}")
    public Task listarTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task crearTask(@RequestBody Task task) {
        return taskService.crearTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        Task existingTask = taskService.getTaskById(id);
        existingTask.setCliente(taskDetails.getCliente());
        existingTask.setServicioSolicitado(taskDetails.getServicioSolicitado());
        existingTask.setProfesional(taskDetails.getProfesional());
        existingTask.setFecha(taskDetails.getFecha());
        existingTask.setHora(taskDetails.getHora());

        return taskService.crearTask(existingTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        try{
            taskService.deleteTaskById(id);
            return ResponseEntity.ok("Reserva eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskPartial(@PathVariable Long id, @RequestBody  Map<String, Object> updates){
        try{
            Task updateTask = taskService.updateTaskPartial(id, updates);
            return ResponseEntity.ok(updateTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}