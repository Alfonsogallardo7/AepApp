import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Competidor } from 'src/app/model/interfaces/competidor.interface';
import { CompetidorService } from 'src/app/services/competidor.service';

export interface DialogCompetidorDetailData {
  competidorId: string;
}

@Component({
  selector: 'app-dialog-competidor-detail',
  templateUrl: './dialog-competidor-detail.component.html',
  styleUrls: ['./dialog-competidor-detail.component.scss']
})
export class DialogCompetidorDetailComponent implements OnInit {

  competidor!: Competidor;
  constructor(
    @Inject(MAT_DIALOG_DATA) private data: DialogCompetidorDetailData,
    private competidorService: CompetidorService
  ) { }

  ngOnInit(): void {
    this.competidorService.getCampetidor(this.data.competidorId).subscribe(competidorResult => {
      this.competidor = competidorResult;
    })
  }

}
