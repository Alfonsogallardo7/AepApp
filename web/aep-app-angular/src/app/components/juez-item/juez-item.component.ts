import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Juez } from 'src/app/model/interfaces/juez.interface';
import swal from "sweetalert2";
import { JuezService } from 'src/app/services/juez.service';

@Component({
  selector: 'app-juez-item',
  templateUrl: './juez-item.component.html',
  styleUrls: ['./juez-item.component.scss']
})
export class JuezItemComponent implements OnInit {
  @Input() juezInput!: Juez;

  constructor(
    private router: Router,
    private juezService: JuezService
  
  ) { }

  ngOnInit(): void {
  }

  onDeleteJuez(juez: Juez) {
    swal
      .fire({
        title: "¿Estás seguro que desea eliminar el juez?",
        text: "Esta opción no se podrá deshacer",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
      })
      .then(resultado => {
        if (resultado.value) {
          // Hicieron click en "Sí"
          this.juezService.deleteJuez(juez.id).subscribe(resp =>  {
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
