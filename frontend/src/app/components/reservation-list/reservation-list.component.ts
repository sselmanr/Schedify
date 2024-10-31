import { Component, OnInit } from '@angular/core';
import { ReservationService, Reservation } from '../../services/reservation.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reservation-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  selectedReservation: Reservation | null = null;

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations() {
    this.reservationService.getReservations().subscribe(
      (data) => {
        // Ordenar las reservas por fecha de servicio antes de asignarlas
        this.reservations = data.sort((a, b) => new Date(a.fecha).getTime() - new Date(b.fecha).getTime());
      },
      (error) => {
        console.error('Error al cargar las reservas', error);
      }
    );
  }

  viewReservation(reservation: Reservation){
    this.selectedReservation = reservation;
  }

  closeModal(){
    this.selectedReservation = null;
  }

  onDeleteReservation(id: number): void{
    this.reservationService.deleteResrevation(id).subscribe(
    () => {
      this.reservations = this.reservations.filter(reservation => reservation.id !== id);
      alert('Reserva eliminada correctamente');
    },
    (error) => {
      console.error('Error al eliminar la reserva:', error);
      alert('Hubo un problema al eliminar la reserva');
    }
    );
  }
}
