import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Competidor, CompetidorResponse } from '../model/interfaces/competidor.interface';
import { AuthService } from './auth.service';

const token = localStorage.getItem('request_token')
const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    // 'Content-Type': 'application/json',
    'Authorization' : `Bearer ${token}`
  })
};

@Injectable({
  providedIn: 'root'
})
export class CompetidorService {

  constructor(
    private http:HttpClient,
    private authService: AuthService
  ) { }


  getCompetidores() {
    return this.http.get<CompetidorResponse>(`http://localhost:8080/athletes/`, DEFAULT_HEADERS);
  }

  getCampetidor(uuidCampetidor: string): Observable<Competidor> {
    return this.http.get<Competidor>(`http://localhost:8080/athletes/${uuidCampetidor}`);
  }

  deleteCompetidor(uuidCompetidor: string): Observable<Competidor> {
    const token = this.authService.getLocalRequestToken();
    const reqHeaders = { Authorization: `Bearer ${token}` };

    return this.http.delete<Competidor>(
      `http://localhost:8080/athletes/${uuidCompetidor}`, DEFAULT_HEADERS
      // {headers: reqHeaders}
    ).pipe(
      catchError(this.handleError)
    );

  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // Se produjo un error de red o del lado del cliente. Manéjelo en consecuencia.
      console.error('An error occurred:', error.error);
    } else {
      // El backend devolvió un código de respuesta incorrecto.
      // El cuerpo de la respuesta puede contener pistas sobre lo que salió mal.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Devuelve un observable con un mensaje de error de cara al usuario.
    return throwError(
      'Something bad happened; please try again later.');
  }
}
