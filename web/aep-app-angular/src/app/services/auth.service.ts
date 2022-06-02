import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthLoginDto } from '../model/dto/login.dto';
import { Observable } from 'rxjs';
import { AuthLoginResponse } from '../model/interfaces/auth-login.interface';

const AUTH_BASE_URL = 'auth';
const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private http: HttpClient) { }

  setLocalRequestToken(token: string) {
    localStorage.setItem('request_token', token)
  }

  getLocalRequestToken(){
    return localStorage.getItem('request_token');
  }

  login(loginDto : AuthLoginDto): Observable<AuthLoginResponse> {
    return this.http.post<AuthLoginResponse>(`${environment.apiBaseUrl}auth/admin-login`, loginDto, DEFAULT_HEADERS);

  }
}
