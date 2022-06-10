// export interface Campeonato {
//     id: string
//     nombre: string
//     cartel: string
//     organizador: string
//     categoriaCompeticion: string
//     cuadranteJueces: any
//     sesiones: any
//     localidad: string
//     provincia: string
//     fechaInicio: string
//     fechaFin: string
//   }


  export interface CampeonatoResponse {
    content: Campeonato[]
    pageable: Pageable
    last: boolean
    totalElements: number
    totalPages: number
    first: boolean
    size: number
    number: number
    sort: Sort2
    numberOfElements: number
    empty: boolean
  }
  
  export interface Campeonato {
    id: string
    nombre: string
    cartel: string
    organizador: string
    categoriaCompeticion: string
    cuadranteJueces: any
    sesiones: any
    localidad: string
    provincia: string
    fechaInicio: string
    fechaFin: string
  }
  
  export interface Pageable {
    sort: Sort
    offset: number
    pageNumber: number
    pageSize: number
    paged: boolean
    unpaged: boolean
  }
  
  export interface Sort {
    empty: boolean
    unsorted: boolean
    sorted: boolean
  }
  
  export interface Sort2 {
    empty: boolean
    unsorted: boolean
    sorted: boolean
  }
  
  export interface NewCampeonatoResponse {
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
}

  