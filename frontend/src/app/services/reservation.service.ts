import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Reservation {
  id: number;
  cliente: string;
  servicioSolicitado: string;
  profesional: string;
  fecha: string;
  hora: string;
}

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/tasks';  // Asegúrate de que esta URL es la correcta

  constructor(private http: HttpClient) { }

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl);
  }

  createReservation(reservation: Reservation): Observable<HttpResponse<any>>{
    return this.http.post<any>(this.apiUrl, reservation, {observe: 'response'});
  }

  deleteResrevation(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {responseType: 'text' as 'json'});
  }
}
