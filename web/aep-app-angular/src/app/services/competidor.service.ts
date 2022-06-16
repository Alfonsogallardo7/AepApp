import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompetidorResponse } from '../model/interfaces/competidor.interface';

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
    private http:HttpClient
  ) { }


  getCompetidores() {
    return this.http.get<CompetidorResponse>(`http://localhost:8080/athletes/`, DEFAULT_HEADERS);
  }
}
