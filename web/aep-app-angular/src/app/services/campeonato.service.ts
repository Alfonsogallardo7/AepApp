import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DialogCampeonatoDetailComponent } from '../components/dialog-campeonato-detail/dialog-campeonato-detail.component';
import { CampeonatoDto } from '../model/dto/campeonato.dto';
import { Campeonato, CampeonatoResponse, NewCampeonatoResponse } from '../model/interfaces/campeonato.interface';
import { AuthService } from './auth.service';

const token = localStorage.getItem('request_token')
const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization' : `Bearer ${token}`
  })
};

const DEFAULT_TOKEN = {

};
@Injectable({
  providedIn: 'root'
})
export class CampeonatoService {
  constructor(private http: HttpClient, private authService: AuthService) { }

  getCampeonatos(): Observable<CampeonatoResponse> {
    return this.http.get<CampeonatoResponse>(`${environment.apiBaseUrl}championships/`);
    // return this.http.get<CampeonatoResponse>(`http://localhost:8080/championships/`);

  }

  getCampeonato(uuidCampeonato: string): Observable<Campeonato> {
    return this.http.get<Campeonato>(`${environment.apiBaseUrl}championships/${uuidCampeonato}`);
  }

  createCampeonato(campeonato: CampeonatoDto, file: File) {
    let requestUrl = `${environment.apiBaseUrl}championships/`;

    let formData = new FormData();
    formData.append('newCompeticion', new Blob([JSON.stringify(campeonato)], { type: 'application/json' }));
    formData.append('file', file);

    //return this.http.post<NewCampeonatoResponse>(requestUrl, formData, DEFAULT_TOKEN).pipe(catchError(this.handleError));
    const request = new HttpRequest("POST",requestUrl, formData, {reportProgress:true, responseType: 'text', headers: new HttpHeaders({Authorization: `Bearer ${token}`})} )
    return this.http.request(request).pipe(catchError(this.handleError));
  }

  deleteCampeonato(uuidCampeonato: string): Observable<Campeonato> {
    const token = this.authService.getLocalRequestToken();
    const reqHeaders = { Authorization: `Bearer ${token}` };

    return this.http.delete<Campeonato>(
      `${environment.apiBaseUrl}championships/${uuidCampeonato}`, DEFAULT_HEADERS
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
