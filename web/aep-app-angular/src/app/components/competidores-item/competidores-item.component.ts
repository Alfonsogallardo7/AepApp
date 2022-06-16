import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Competidor } from 'src/app/model/interfaces/competidor.interface';
import { CompetidorService } from 'src/app/services/competidor.service';

@Component({
  selector: 'app-competidores-item',
  templateUrl: './competidores-item.component.html',
  styleUrls: ['./competidores-item.component.scss']
})
export class CompetidoresItemComponent implements OnInit {
  @Input() competidorInput!: Competidor;

  constructor(
    private router: Router,
    private competidorService: CompetidorService

  ) { }

  ngOnInit(): void {
  }

}
