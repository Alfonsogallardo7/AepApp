class LoginResponse {
  LoginResponse({
    required this.nombre,
    required this.apellidos,
    required this.email,
    required this.username,
    required this.fotoPerfil,
    required this.rol,
    required this.token,
  });
  late final String nombre;
  late final String apellidos;
  late final String email;
  late final String username;
  late final String fotoPerfil;
  late final String rol;
  late final String token;
  
  LoginResponse.fromJson(Map<String, dynamic> json){
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    email = json['email'];
    username = json['username'];
    fotoPerfil = json['fotoPerfil'];
    rol = json['rol'];
    token = json['token'];
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
    return _data;
  }
}

class LoginDto {
  LoginDto({
    required this.email,
    required this.password,
  });
  late final String email;
  late final String password;

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['email'] = email;
    _data['password'] = password;
    return _data;
  }
}