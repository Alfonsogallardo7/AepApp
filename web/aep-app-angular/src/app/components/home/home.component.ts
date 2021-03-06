import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  goCampeonatos() {
    this.router.navigateByUrl('championships/');
  }

  goJudges() {
    this.router.navigateByUrl('judges/');
  }

  goAtletas() {
    this.router.navigateByUrl('athletes/');
  }

}
