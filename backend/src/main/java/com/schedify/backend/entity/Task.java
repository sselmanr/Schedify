package com.schedify.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String cliente;
  private String servicioSolicitado;
  private String profesional;
  private String fecha;
  private String hora;

  public Long getId(){
    return id; 
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getCliente() {
    return cliente; 
  }
  
  public void setCliente(String cliente) {
    this.cliente = cliente;
  }
  
  public String getServicioSolicitado() {
    return servicioSolicitado; 
  }
  
  public void setServicioSolicitado(String servicioSolicitado) {
    this.servicioSolicitado = servicioSolicitado;
  }
  
  public String getProfesional() {
    return profesional; 
  }
  
  public void setProfesional(String profesional) {
    this.profesional = profesional;
  }
  
  public String getFecha() {
    return fecha; 
  }
  
  public void setFecha(String fecha) {
    this.fecha = fecha;
  }
  
  public String getHora() {
    return hora; 
  }
  
  public void setHora(String hora) {
    this.hora = hora;
  }

}