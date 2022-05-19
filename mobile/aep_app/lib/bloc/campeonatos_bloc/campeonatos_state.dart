import 'package:aep_app/models/campeonatos_response.dart';
import 'package:equatable/equatable.dart';

abstract class CampeonatosState extends Equatable {
  const CampeonatosState();
  
  @override
  List<Object> get props => [];
}

class CampeonatosInitial extends CampeonatosState {}


class CampeonatosFetched extends CampeonatosState {
  final List<Campeonatos> campeonatos;

  const CampeonatosFetched(this.campeonatos);

  @override
  List<Object> get props => [campeonatos];
}

class CampeonatosFetchError extends CampeonatosState {
  final String message;
  const CampeonatosFetchError(this.message);

  @override
  List<Object> get props => [message];
}