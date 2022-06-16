import 'package:aep_app/bloc/me_bloc/me_bloc.dart';
import 'package:aep_app/bloc/me_bloc/me_event.dart';
import 'package:aep_app/bloc/me_bloc/me_state.dart';
import 'package:aep_app/models/me_response.dart';
import 'package:aep_app/repository/auth_repository/auth_repository.dart';
import 'package:aep_app/repository/auth_repository/auth_repository_impl.dart';
import 'package:aep_app/ui/screen/error_screen.dart';
import 'package:aep_app/ui/widgets/aep_bar.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class PerfilScreen extends StatefulWidget {
  const PerfilScreen({Key? key}) : super(key: key);

  @override
  State<PerfilScreen> createState() => _PerfilScreenState();
}

class _PerfilScreenState extends State<PerfilScreen> {
  late AuthRepository authRepository;

  @override
  void initState() {
    super.initState();
    authRepository = AuthrepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        body: Container(
          alignment: Alignment.center,
          child: Text('Lo sentimos, esta sección aún no esta disponible'),
        )); // body: BlocProvider(
    //   create: (context) {
    //     return MeBloc(authRepository)..add(FetchMeWithType());
    //   },
    //   child: _createPopular(context),
    // ));
  }

  Widget _createPopular(BuildContext context) {
    return BlocBuilder<MeBloc, MeState>(
      builder: (context, state) {
        if (state is MeInitial) {
          return const Center(child: CircularProgressIndicator());
        } else if (state is MeFetchError) {
          return ErrorScreen(
            message: state.message,
            retry: () {
              context.watch<MeBloc>().add(FetchMeWithType());
            },
          );
        } else if (state is MeFetched) {
          return _createMeViewItem(context, state.me);
        } else {
          return const Text('Not support');
        }
      },
    );
  }

  Widget _createMeViewItem(BuildContext context, MeResponse me) {
    return Container(
      child: Column(
        children: [
          Container(
            margin: const EdgeInsets.only(top: 12),
            width: 90.0,
            height: 90.0,
            decoration: BoxDecoration(
              shape: BoxShape.circle,
              image: DecorationImage(
                  fit: BoxFit.cover, image: NetworkImage(me.fotoPerfil)),
            ),
          ),
        ],
      ),
    );
  }
}
