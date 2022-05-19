import 'package:aep_app/repository/campeonato_repository/campeonato_repository.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository_impl.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({ Key? key }) : super(key: key);

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
    return Scaffold(
      body: Container());
        // appBar: const MenuScreen(),
        // body: BlocProvider(
        //   create: (context) {
        //     return PostsBloc(movieRepository)..add(FetchPostsWithType());
        //   },
        //   child: Scaffold(body: _createPopular(context)),
        // ));
  }
}