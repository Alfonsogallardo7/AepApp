import 'dart:convert';

import 'package:aep_app/models/campeonatos_response.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository.dart';
import 'package:aep_app/repository/constants.dart';
import 'package:http/http.dart';
import 'package:http/http.dart' as http;
import 'package:http_parser/http_parser.dart';
import 'package:shared_preferences/shared_preferences.dart';
class CampeonatoRepositoryImpl extends CampeonatoRepository {
    final Client _client = Client();

  @override
  Future<List<Campeonatos>> getCampeonatos() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    var token = preferences.getString (Constant.token);
    final response = await _client
        .get(Uri.parse('http://10.0.2.2:8080/championships/'), headers: {
      'Authorization':
          'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI3ZjAwMDAwMS04MGUwLTE5MmYtODE4MC1lMGQ5YmM0MzAwMDAiLCJpYXQiOjE2NTMxMjYyMDksIm5vbWJyZSI6IkFsZm9uc28iLCJhcGVsbGlkb3MiOiJHYWxsYXJkbyBSb2Ryw61ndWV6Iiwicm9sZSI6IkFETUlOSVNUUkFET1IifQ.ZzwILpFqYKTfS3eNl5-v8YzLoY90LHwOEFTKWsY3kTi9XlCQd1YwKTnzhv72656w'
    });
    if (response.statusCode == 200) {
      return CampeonatosResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fail to load movies');
    }
  }

}