class RegisterResponse {
  RegisterResponse({
    required this.id,
    required this.nombre,
    required this.apellidos,
    required this.fotoPerfil,
    required this.username,
    required this.email,
    required this.rol,
  });
  late final String id;
  late final String nombre;
  late final String apellidos;
  late final String fotoPerfil;
  late final String username;
  late final String email;
  late final String rol;
  
  RegisterResponse.fromJson(Map<String, dynamic> json){
    id = json['id'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    fotoPerfil = json['fotoPerfil'];
    username = json['username'];
    email = json['email'];
    rol = json['rol'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['fotoPerfil'] = fotoPerfil;
    _data['username'] = username;
    _data['email'] = email;
    _data['rol'] = rol;
    return _data;
  }
}