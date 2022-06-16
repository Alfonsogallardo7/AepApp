import 'package:aep_app/models/register_dto.dart';
import 'package:equatable/equatable.dart';

abstract class RegisterEvent extends Equatable {
  const RegisterEvent();

  @override
  List<Object> get props => [];
}

class DoRegisterEvent extends RegisterEvent {
  final RegisterDto registerDto;
  final String imagePath;

  const DoRegisterEvent(this.registerDto, this.imagePath);
}