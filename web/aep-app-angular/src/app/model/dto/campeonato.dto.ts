export class CampeonatoDto {
    id: string;
    nombre: string;
    cartel: string;
    organizador: string;
    categoriaCompeticion: string;
    cuadranteJueces?: any;
    sesiones?: any;
    localidad: string;
    provincia: string;
    fechaInicio: string;
    fechaFin: string;

    constructor() {
        this.id = '';
        this.nombre = '';
        this.cartel = '';
        this.organizador = '';
        this.categoriaCompeticion = '';
        this.cuadranteJueces = '';
        this.sesiones = '';
        this.localidad = '';
        this.provincia = '';
        this.fechaInicio = '';
        this.fechaFin = '';
    }

}