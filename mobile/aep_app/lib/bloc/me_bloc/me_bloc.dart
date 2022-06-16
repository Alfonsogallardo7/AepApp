import 'package:aep_app/bloc/me_bloc/me_event.dart';
import 'package:aep_app/bloc/me_bloc/me_state.dart';
import 'package:aep_app/repository/auth_repository/auth_repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class MeBloc extends Bloc<MeEvent, MeState> {
  final AuthRepository authRepository;

  MeBloc(this.authRepository) : super(MeInitial()) {
    on<FetchMeWithType>(_meFetched);
  }

  void _meFetched(FetchMeWithType event, Emitter<MeState> emit) async {
    try {
      final me = await authRepository.me();
      emit(MeFetched(me));
      return;
    } on Exception catch (e) {
      emit(MeFetchError(e.toString()));
    }
  }
}