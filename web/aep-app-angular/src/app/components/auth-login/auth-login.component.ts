import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthLoginDto } from 'src/app/model/dto/login.dto';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-auth-login',
  templateUrl: './auth-login.component.html',
  styleUrls: ['./auth-login.component.scss']
})
export class AuthLoginComponent implements OnInit {

  loginDto= new AuthLoginDto();
  loginForm = new FormGroup({
    email: new FormControl ('' , [Validators.required, Validators.email]),
    password: new FormControl ('', [Validators.required]),

  });
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  doLogin() {
    this.loginDto = this.loginForm.value;
    this.authService.login(this.loginDto).subscribe(loginResponse => {
      this.authService.setLocalRequestToken(loginResponse.token)
      console.log(loginResponse);
      
    })
  }

}
