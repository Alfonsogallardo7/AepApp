import 'package:aep_app/models/campeonatos_response.dart';

abstract class CampeonatoRepository {
  Future<List<Campeonatos>> getCampeonatos();
}