import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservation-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './reservation-form.component.html',
  styleUrl: './reservation-form.component.css'
})
export class ReservationFormComponent {
  reservationForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    private router: Router
  ){
    this.reservationForm = this.fb.group({
      cliente: ['', Validators.required],
      servicioSolicitado: ['', Validators.required],
      profesional: ['', Validators.required],
      fecha: ['', Validators.required],
      hora: ['', Validators.required]
    });
  }

  onSubmit(): void{
    if(this.reservationForm.valid){
      this.reservationService.createReservation(this.reservationForm.value).subscribe(
        response => {
          if(response.status === 201 || response.status === 200){
            alert('Reserva creada exitosamente.');
            this.router.navigate(['/reservations']);
          } else {
            alert(`Hubo un problema al crear la reserva. Código de estado: ${response.status}`);
          }
      },
      error => {
        console.error('Error al crear la resreva', error);
        alert('Hubo un error en la creación de la reserva.');
      });
    }
  }

}
