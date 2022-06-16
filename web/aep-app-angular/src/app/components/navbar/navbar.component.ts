import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/auth/admin-login');
  }

  goCampeonatos() {
    this.router.navigateByUrl('championships/');
  }

  goHome() {
    this.router.navigateByUrl('home/');
  }

  goJudges() {
    this.router.navigateByUrl('judges/');
  }

}
