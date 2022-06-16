import { Component, OnInit } from '@angular/core';
import { Competidor } from 'src/app/model/interfaces/competidor.interface';
import { CompetidorService } from 'src/app/services/competidor.service';

@Component({
  selector: 'app-competidores-list',
  templateUrl: './competidores-list.component.html',
  styleUrls: ['./competidores-list.component.scss']
})
export class CompetidoresListComponent implements OnInit {

  competidores: Competidor[] = [];
  constructor(
    private competidorService: CompetidorService
  ) { }

  ngOnInit(): void {
    this.competidorService.getCompetidores().subscribe(competidoresRespons => {
      this.competidores = competidoresRespons.content;
    });
  }

}
