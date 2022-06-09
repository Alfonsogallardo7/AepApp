import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Campeonato, CampeonatoResponse } from 'src/app/model/interfaces/campeonato.interface';
import { CampeonatoService } from 'src/app/services/campeonato.service';

export interface DialogCampeonatoDetailData {
  campeonatoId: string;
}
@Component({
  selector: 'app-dialog-campeonato-detail',
  templateUrl: './dialog-campeonato-detail.component.html',
  styleUrls: ['./dialog-campeonato-detail.component.scss']
})
export class DialogCampeonatoDetailComponent implements OnInit {

  campeonato! : Campeonato;
  constructor(
    @Inject(MAT_DIALOG_DATA) private data: DialogCampeonatoDetailData,
    private campeonatoService: CampeonatoService
  ) { }

  ngOnInit(): void {

    console.log(this.data.campeonatoId);
    this.campeonatoService.getCampeonato(this.data.campeonatoId).subscribe (campeonatoResult => {
      this.campeonato = campeonatoResult;
    })

  }

}
