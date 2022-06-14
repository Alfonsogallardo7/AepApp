import { Component, Input, OnInit } from '@angular/core';
import swal from "sweetalert2";
import { Campeonato, CampeonatoResponse } from 'src/app/model/interfaces/campeonato.interface';
import { CampeonatoService } from 'src/app/services/campeonato.service';
import { DialogCampeonatoDetailComponent } from '../dialog-campeonato-detail/dialog-campeonato-detail.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-campeonato-item',
  templateUrl: './campeonato-item.component.html',
  styleUrls: ['./campeonato-item.component.scss']
})
export class CampeonatoItemComponent implements OnInit {
  @Input() campeonatoInput!: Campeonato;

  constructor(private campeonatoService: CampeonatoService,
    private dialog: MatDialog,
    private router: Router) { }

  ngOnInit(): void {
  }

  openDialogCampeonatoDetail(){
    this.dialog.open(DialogCampeonatoDetailComponent, {
      height: 'auto',
      width: '40%',
      data: {campeonatoId: this.campeonatoInput?.id}
    })
  }

  onDeleteCampeonato(campeonato: Campeonato) {
    swal
      .fire({
        title: "¿Estás seguro que desea eliminar el campeonato?",
        text: "Esta opción no se podrá deshacer",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
      })
      .then(resultado => {
        if (resultado.value) {
          // Hicieron click en "Sí"
          this.campeonatoService.deleteCampeonato(campeonato.id).subscribe(resp =>  {
            console.log("*se elimina la venta*");
            this.router.navigateByUrl('home/')
          });
          
        } else {
          // Dijeron que no
          console.log("*NO se elimina la venta*");
        }
      });
  }

  goNewCampeonato() {
    this.router.navigateByUrl('championships/new');
  }

}
