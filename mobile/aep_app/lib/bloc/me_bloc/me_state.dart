import 'package:aep_app/models/me_response.dart';
import 'package:equatable/equatable.dart';

abstract class MeState extends Equatable {
  const MeState();
  
  @override
  List<Object> get props => [];
}

class MeInitial extends MeState {}


class MeFetched extends MeState {
  final MeResponse me;

  const MeFetched(this.me);

  @override
  List<Object> get props => [me];
}

class MeFetchError extends MeState {
  final String message;
  const MeFetchError(this.message);

  @override
  List<Object> get props => [message];
}