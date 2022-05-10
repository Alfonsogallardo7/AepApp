import 'package:aep_app/models/login_response.dart';
import 'package:aep_app/models/register_dto.dart';
import 'package:aep_app/models/register_response.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);
    Future<RegisterResponse> register (RegisterDto registerDto, String imagePath);

}