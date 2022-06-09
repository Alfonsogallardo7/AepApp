import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DialogCampeonatoDetailComponent } from '../components/dialog-campeonato-detail/dialog-campeonato-detail.component';
import { CampeonatoDto } from '../model/dto/campeonato.dto';
import { Campeonato, CampeonatoResponse, NewCampeonatoResponse } from '../model/interfaces/campeonato.interface';
import { AuthService } from './auth.service';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
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

    return this.http.post<NewCampeonatoResponse>(requestUrl, formData);
  }

  deleteCampeonato(uuidCampeonato: string) {
    const token = this.authService.getLocalRequestToken();
    const reqHeaders = { Authorization: `Bearer ${token}` };

    return this.http.delete(
      `${environment.apiBaseUrl}championships/${uuidCampeonato}`, DEFAULT_HEADERS
      // {headers: reqHeaders}
    );

  }
}
