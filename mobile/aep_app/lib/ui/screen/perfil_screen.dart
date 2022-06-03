import 'package:aep_app/ui/widgets/aep_bar.dart';
import 'package:flutter/material.dart';

class PerfilScreen extends StatefulWidget {
  const PerfilScreen({ Key? key }) : super(key: key);

  @override
  State<PerfilScreen> createState() => _PerfilScreenState();
}

class _PerfilScreenState extends State<PerfilScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(child: Text('perfil screen'),));
        // body: BlocProvider(
        //   create: (context) {
        //     return CampeonatosBloc(campeonatoRepository)..add(FetchCampeonatosWithType());
        //   },
        //   child: Scaffold(body: _createList(context)),
        // ));
  }
}