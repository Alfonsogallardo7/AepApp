export interface JuezResponse {
    content: Juez[]
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
  
  export interface Juez {
    id: string
    nombre: string
    apellidos: string
    tipoJuez: string
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
    sorted: boolean
    unsorted: boolean
  }
  
  export interface Sort2 {
    empty: boolean
    sorted: boolean
    unsorted: boolean
  }
  