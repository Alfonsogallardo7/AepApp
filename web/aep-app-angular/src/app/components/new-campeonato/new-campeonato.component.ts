import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CampeonatoDto } from 'src/app/model/dto/campeonato.dto';
import provinciasJson from "../../../assets/json/provincias.json";
import localidadesJson from "../../../assets/json/localidades.json";
import { CampeonatoService } from 'src/app/services/campeonato.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-new-campeonato',
  templateUrl: './new-campeonato.component.html',
  styleUrls: ['./new-campeonato.component.scss']
})
export class NewCampeonatoComponent implements OnInit {

  file: any;
  listaProvincias = provinciasJson;
  listaLocalidades = localidadesJson;
  categoriaCompeticion = [
    { value: 'Aep 1', viewValue: 'Aep 1' },
    { value: 'Aep 2', viewValue: 'Aep 2' },
    { value: 'Aep 3', viewValue: 'Aep 3' },
  ]
  campeonatoDto = new CampeonatoDto();
  campeonatoForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    organizador: new FormControl(''),
    cartel: new FormControl(''),
    categoriaCompeticion: new FormControl('', [Validators.required]),
    cuadranteJueces: new FormControl(''),
    sesiones: new FormControl(''),
    localidad: new FormControl('', [Validators.required]),
    provincia: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
  });
  constructor(
    private campeonatoService: CampeonatoService,
    private router: Router,
    ) { }

  ngOnInit(): void {
  }

  doCreate() {
    // this.campeonatoForm.value.fechaInicio = this.parserFormDate(this.campeonatoForm.value.fechaInicio);
    this.file = this.parseFile(this.campeonatoForm.value.cartel);
    this.campeonatoDto = this.campeonatoForm.value;

    this.campeonatoService.createCampeonato(this.campeonatoDto, this.file)
    
    this.router.navigateByUrl('/home');
    

  }

  parseFile(fileToParse: any) {
    const fileParse = fileToParse.slice(12, fileToParse.length);
    return fileParse;
  }

  parserFormDate(date: any) {
    const birthdate = new Date(date.year, date.month, date.day);
    return birthdate.toISOString();
  }

}
