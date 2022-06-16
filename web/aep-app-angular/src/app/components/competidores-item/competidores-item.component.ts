import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import swal from "sweetalert2";
import { Competidor } from 'src/app/model/interfaces/competidor.interface';
import { CompetidorService } from 'src/app/services/competidor.service';
import { DialogCompetidorDetailComponent } from '../dialog-competidor-detail/dialog-competidor-detail.component';

@Component({
  selector: 'app-competidores-item',
  templateUrl: './competidores-item.component.html',
  styleUrls: ['./competidores-item.component.scss']
})
export class CompetidoresItemComponent implements OnInit {
  @Input() competidorInput!: Competidor;

  constructor(
    private router: Router,
    private dialog: MatDialog,
    private competidorService: CompetidorService

  ) { }

  ngOnInit(): void {
  }

  openDialogCampetidorDetail(){
    this.dialog.open(DialogCompetidorDetailComponent, {
      height: 'auto',
      width: '40%',
      data: {competidorId: this.competidorInput?.id}
    })
  }

  onDeleteCompetidor(competidor: Competidor) {
    swal
      .fire({
        title: "¿Estás seguro que desea eliminar el competidor?",
        text: "Esta opción no se podrá deshacer",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
      })
      .then(resultado => {
        if (resultado.value) {
          // Hicieron click en "Sí"
          this.competidorService.deleteCompetidor(competidor.id).subscribe(resp =>  {
            console.log("*se elimina la venta*");
            this.router.navigateByUrl('home/')
          });
          
        } else {
          // Dijeron que no
          console.log("*NO se elimina la venta*");
        }
      });
  }

}
