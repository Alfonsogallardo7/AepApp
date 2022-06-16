import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { JuezResponse } from '../model/interfaces/juez.interface';
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
export class JuezService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getJueces() {
    return this.http.get<JuezResponse>(`http://localhost:8080/judges/`, DEFAULT_HEADERS);
  }
}
