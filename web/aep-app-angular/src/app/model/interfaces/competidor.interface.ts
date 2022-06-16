export interface CompetidorResponse {
    content:          Competidor[];
    pageable:         Pageable;
    last:             boolean;
    totalPages:       number;
    totalElements:    number;
    first:            boolean;
    size:             number;
    number:           number;
    sort:             Sort;
    numberOfElements: number;
    empty:            boolean;
}

export interface Competidor {
    id:              string;
    nombre:          string;
    apellidos:       string;
    categoriaPeso:   string;
    foto:            string;
    club:            string;
    fechaNacimiento: Date;
    marcasSq:        number;
    marcasBp:        number;
    marcasDl:        number;
    competiciones:   any[];
}

export interface Pageable {
    sort:       Sort;
    offset:     number;
    pageNumber: number;
    pageSize:   number;
    paged:      boolean;
    unpaged:    boolean;
}

export interface Sort {
    empty:    boolean;
    unsorted: boolean;
    sorted:   boolean;
}
