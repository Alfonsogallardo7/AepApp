class CampeonatosResponse {
  CampeonatosResponse({
    required this.content,
    required this.pageable,
    required this.last,
    required this.totalElements,
    required this.totalPages,
    required this.first,
    required this.size,
    required this.number,
    required this.sort,
    required this.numberOfElements,
    required this.empty,
  });
  late final List<Campeonatos> content;
  late final Pageable pageable;
  late final bool last;
  late final int totalElements;
  late final int totalPages;
  late final bool first;
  late final int size;
  late final int number;
  late final Sort sort;
  late final int numberOfElements;
  late final bool empty;

  CampeonatosResponse.fromJson(Map<String, dynamic> json) {
    content =
        List.from(json['content']).map((e) => Campeonatos.fromJson(e)).toList();
    pageable = Pageable.fromJson(json['pageable']);
    last = json['last'];
    totalElements = json['totalElements'];
    totalPages = json['totalPages'];
    first = json['first'];
    size = json['size'];
    number = json['number'];
    sort = Sort.fromJson(json['sort']);
    numberOfElements = json['numberOfElements'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['content'] = content.map((e) => e.toJson()).toList();
    _data['pageable'] = pageable.toJson();
    _data['last'] = last;
    _data['totalElements'] = totalElements;
    _data['totalPages'] = totalPages;
    _data['first'] = first;
    _data['size'] = size;
    _data['number'] = number;
    _data['sort'] = sort.toJson();
    _data['numberOfElements'] = numberOfElements;
    _data['empty'] = empty;
    return _data;
  }
}

class Campeonatos {
  Campeonatos({
    required this.id,
    required this.nombre,
    required this.cartel,
    required this.organizador,
    required this.categoriaCompeticion,
    required this.cuadranteJueces,
    required this.sesiones,
    required this.localidad,
    required this.provincia,
    required this.fechaInicio,
    required this.fechaFin,
  });
  late final String id;
  late final String nombre;
  late final String cartel;
  late final String organizador;
  late final String categoriaCompeticion;
  late final String cuadranteJueces;
  late final String sesiones;
  late final String localidad;
  late final String provincia;
  late final String fechaInicio;
  late final String fechaFin;

  Campeonatos.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    cartel = json['cartel'];
    organizador = json['organizador'];
    categoriaCompeticion = json['categoriaCompeticion'];
    cuadranteJueces = json['cuadranteJueces'];
    sesiones = json['sesiones'];
    localidad = json['localidad'];
    provincia = json['provincia'];
    fechaInicio = json['fechaInicio'];
    fechaFin = json['fechaFin'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['cartel'] = cartel;
    _data['organizador'] = organizador;
    _data['categoriaCompeticion'] = categoriaCompeticion;
    _data['cuadranteJueces'] = cuadranteJueces;
    _data['sesiones'] = sesiones;
    _data['localidad'] = localidad;
    _data['provincia'] = provincia;
    _data['fechaInicio'] = fechaInicio;
    _data['fechaFin'] = fechaFin;
    return _data;
  }
}

class Pageable {
  Pageable({
    required this.sort,
    required this.offset,
    required this.pageNumber,
    required this.pageSize,
    required this.paged,
    required this.unpaged,
  });
  late final Sort sort;
  late final int offset;
  late final int pageNumber;
  late final int pageSize;
  late final bool paged;
  late final bool unpaged;

  Pageable.fromJson(Map<String, dynamic> json) {
    sort = Sort.fromJson(json['sort']);
    offset = json['offset'];
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['sort'] = sort.toJson();
    _data['offset'] = offset;
    _data['pageNumber'] = pageNumber;
    _data['pageSize'] = pageSize;
    _data['paged'] = paged;
    _data['unpaged'] = unpaged;
    return _data;
  }
}

class Sort {
  Sort({
    required this.empty,
    required this.unsorted,
    required this.sorted,
  });
  late final bool empty;
  late final bool unsorted;
  late final bool sorted;

  Sort.fromJson(Map<String, dynamic> json) {
    empty = json['empty'];
    unsorted = json['unsorted'];
    sorted = json['sorted'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['empty'] = empty;
    _data['unsorted'] = unsorted;
    _data['sorted'] = sorted;
    return _data;
  }
}
