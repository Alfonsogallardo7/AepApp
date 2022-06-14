class MeResponse {
  MeResponse({
    required this.nombre,
    required this.apellidos,
    required this.email,
    required this.username,
    required this.fotoPerfil,
    required this.rol,
    required this.token,
    required this.direccion,
    required this.codigoPostal,
    required this.localidad,
    required this.fechaNacimiento,
  });
  late final String nombre;
  late final String apellidos;
  late final String email;
  late final String username;
  late final String fotoPerfil;
  late final String rol;
  late final String token;
  late final String direccion;
  late final String codigoPostal;
  late final String localidad;
  late final String fechaNacimiento;
  
  MeResponse.fromJson(Map<String, dynamic> json){
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    email = json['email'];
    username = json['username'];
    fotoPerfil = json['fotoPerfil'];
    rol = json['rol'];
    token = json['token'];
    direccion = json['direccion'];
    codigoPostal = json['codigoPostal'];
    localidad = json['localidad'];
    fechaNacimiento = json['fechaNacimiento'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['email'] = email;
    _data['username'] = username;
    _data['fotoPerfil'] = fotoPerfil;
    _data['rol'] = rol;
    _data['token'] = token;
    _data['direccion'] = direccion;
    _data['codigoPostal'] = codigoPostal;
    _data['localidad'] = localidad;
    _data['fechaNacimiento'] = fechaNacimiento;
    return _data;
  }
}