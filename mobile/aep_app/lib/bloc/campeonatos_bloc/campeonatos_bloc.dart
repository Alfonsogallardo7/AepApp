import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_event.dart';
import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_state.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class CampeonatosBloc extends Bloc<CampeonatosEvent, CampeonatosState> {
  final CampeonatoRepository campeonatosRepository;

  CampeonatosBloc(this.campeonatosRepository) : super(CampeonatosInitial()) {
    on<FetchCampeonatosWithType>(_campeonatosFetched);
  }

  void _campeonatosFetched(FetchCampeonatosWithType event, Emitter<CampeonatosState> emit) async {
    try {
      final campeonatos = await campeonatosRepository.getCampeonatos();
      emit(CampeonatosFetched(campeonatos));
      return;
    } on Exception catch (e) {
      emit(CampeonatosFetchError(e.toString()));
    }
  }
}
