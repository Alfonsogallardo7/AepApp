import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_bloc.dart';
import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_event.dart';
import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_state.dart';
import 'package:aep_app/models/campeonatos_response.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository_impl.dart';
import 'package:aep_app/ui/screen/error_screen.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';
import 'package:aep_app/ui/utils/color_style.dart';
import 'package:aep_app/ui/widgets/aep_bar.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late CampeonatoRepository campeonatoRepository;
  bool isLikeAnimation = false;
  @override
  void initState() {
    super.initState();
    campeonatoRepository = CampeonatoRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) {
        return CampeonatosBloc(campeonatoRepository)
          ..add(FetchCampeonatosWithType());
      },
      child: Scaffold(
        body: _createList(context),
      ),
    );
  }

  Widget _createList(BuildContext context) {
    return BlocBuilder<CampeonatosBloc, CampeonatosState>(
      builder: (context, state) {
        if (state is CampeonatosInitial) {
          return const Center(child: CircularProgressIndicator());
        } else if (state is CampeonatosFetchError) {
          return ErrorScreen(
            message: state.message,
            retry: () {
              context.watch<CampeonatosBloc>().add(FetchCampeonatosWithType());
            },
          );
        } else if (state is CampeonatosFetched) {
          return _createPopularView(context, state.campeonatos);
        } else {
          return Center(child: Text("No hay campeonatos actualmente"));
        }
      },
    );
  }

  Widget _createPopularView(
      BuildContext context, List<Campeonatos> campeonatos) {
    // final contentHeight = 4.0 * (MediaQuery.of(context).size.width / 2.4) / 3;
    return SizedBox(
      height: MediaQuery.of(context).size.height,
      child: ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          return _campeonatos(context, campeonatos[index]);
        },
        // padding: const EdgeInsets.only(left: 16.0, right: 16.0),
        scrollDirection: Axis.vertical,
        separatorBuilder: (context, index) => const VerticalDivider(
          color: Colors.transparent,
          width: 6.0,
        ),
        itemCount: campeonatos.length,
      ),
    );
  }

  Widget _campeonatos(BuildContext context, Campeonatos campeonatos) {
    final width = MediaQuery.of(context).size.width / 2.6;
    return Container(
      color: Colors.white,
      padding: EdgeInsets.symmetric(vertical: 10),
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.symmetric(vertical: 4, horizontal: 16)
                .copyWith(right: 0),
          ),
          GestureDetector(
            child: Container(
              alignment: Alignment.center,
              height: MediaQuery.of(context).size.height * 0.45,
              width: MediaQuery.of(context).size.width / 1.4,
              child: ClipRRect(
                borderRadius: BorderRadius.circular(15),
                child: CachedNetworkImage(
                  placeholder: (context, url) => const Center(
                    child: CircularProgressIndicator(),
                  ),
                  imageUrl: campeonatos.cartel,
                  fit: BoxFit.fill,
                ),
              ),
            ),
          ),
          Container(
            padding: const EdgeInsets.symmetric(
              horizontal: 16,
            ),
            child: Column(
              children: [
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.only(top: 20, left: 14, right: 14),
                  alignment: Alignment.center,
                  child: RichText(
                    textAlign: TextAlign.center,
                    text: TextSpan(
                        style: const TextStyle(
                          color: Colors.black,
                        ),
                        children: [
                          TextSpan(
                            text: campeonatos.nombre,
                            style: const TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ]),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
