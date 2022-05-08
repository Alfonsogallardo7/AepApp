import 'package:aep_app/models/login_response.dart';
import 'package:equatable/equatable.dart';

abstract class LoginEvent extends Equatable {
  const LoginEvent();

  @override
  List<Object> get props => [];
}

class DoLoginEvent extends LoginEvent {
  final LoginDto loginDto;

  const DoLoginEvent(this.loginDto);
}