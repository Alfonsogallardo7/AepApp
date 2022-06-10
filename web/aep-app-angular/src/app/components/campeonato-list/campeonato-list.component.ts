import { Component, OnInit } from '@angular/core';
import { Campeonato, CampeonatoResponse } from 'src/app/model/interfaces/campeonato.interface';
import { CampeonatoService } from 'src/app/services/campeonato.service';

@Component({
  selector: 'app-campeonato-list',
  templateUrl: './campeonato-list.component.html',
  styleUrls: ['./campeonato-list.component.scss']
})
export class CampeonatoListComponent implements OnInit {

  campeonatos: Campeonato[] = [];
  constructor(
    private campeonatoService: CampeonatoService
  ) { }

  ngOnInit(): void {
    this.campeonatoService.getCampeonatos().subscribe(campeonatosRespons => {
      this.campeonatos = campeonatosRespons.content;
    });

  }

}
