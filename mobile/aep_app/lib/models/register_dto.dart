class RegisterDto {
  String? nombre;
  String? apellidos;
  String? email;
  String? password;
  String? password2;
  String? username;
  String? dni;
  String? direccion;
  String? codigoPostal;
  String? localidad;
  String? telefono;
  String? fechaNacimiento;

  RegisterDto(
      {this.nombre,
      this.apellidos,
      this.email,
      this.password,
      this.password2,
      this.username,
      this.dni,
      this.codigoPostal,
      this.direccion,
      this.localidad,
      this.telefono,
      this.fechaNacimiento});

  RegisterDto.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    email = json['email'];
    password = json['password'];
    password2 = json['password2'];
    username = json['username'];
    dni = json['dni'];
    direccion = json['direccion'];
    codigoPostal = json['codigoPostal'];
    localidad = json['localidad'];
    telefono = json['telefono'];
    fechaNacimiento = json['fechaNacimiento'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['nombre'] = nombre;
    data['apellidos'] = apellidos;
    data['email'] = email;
    data['password'] = password;
    data['password2'] = password2;
    data['username'] = username;
    data['dni'] = dni;
    data['direccion'] = direccion;
    data['codigoPostal'] = codigoPostal;
    data['localidad'] = localidad;
    data['telefono'] = telefono;
    data['fechaNacimiento'] = fechaNacimiento;
    return data;
  }
}
