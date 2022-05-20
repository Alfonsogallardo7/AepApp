import 'package:aep_app/bloc/login_bloc/login_bloc.dart';
import 'package:aep_app/models/login_response.dart';
import 'package:aep_app/repository/auth_repository/auth_repository.dart';
import 'package:aep_app/repository/auth_repository/auth_repository_impl.dart';
import 'package:aep_app/bloc/login_bloc/login_event.dart';
import 'package:aep_app/bloc/login_bloc/login_state.dart';
import 'package:aep_app/repository/constants.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  bool _isObscure = true;
  late AuthRepository authRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  void initState() {
    authRepository = AuthrepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) {
          return LoginBloc(authRepository);
        },
        child: _createBody(context));
  }

  _createBody(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
            padding: const EdgeInsets.all(20),
            child: BlocConsumer<LoginBloc, LoginState>(
                listenWhen: (context, state) {
              return state is LoginSuccessState || state is LoginErrorState;
            }, listener: (context, state) async {
              if (state is LoginSuccessState) {
                SharedPreferences preferences =
                    await SharedPreferences.getInstance();
                preferences.setString(
                    Constant.token, state.loginResponse.token);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const MenuScreen()),
                );
              } else if (state is LoginErrorState) {
                _showSnackbar(context, state.message);
              }
            }, buildWhen: (context, state) {
              return state is LoginInitialState || state is LoginLoadingState;
            }, builder: (ctx, state) {
              if (state is LoginInitialState) {
                return buildForm(ctx);
              } else if (state is LoginLoadingState) {
                return const Center(child: CircularProgressIndicator());
              } else {
                return buildForm(ctx);
              }
            })),
      ),
      backgroundColor: Colors.white,
    );
  }

  void _showSnackbar(BuildContext context, String message) {
    final snackBar = SnackBar(
      content: Text(message),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  Widget buildForm(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Image.asset(
            'assets/images/logo.jpeg',
            width: 269,
            height: 144,
          ),
          SingleChildScrollView(
            child: SizedBox(
              width: 700,
              child: SizedBox(
                height: 400,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                        padding: EdgeInsets.only(top: 20),
                        child: const Text(
                          'Iniciar Sesión',
                          style: TextStyle(
                              fontWeight: FontWeight.bold, fontSize: 22),
                        )),
                    Container(
                      alignment: Alignment.center,
                      width: MediaQuery.of(context).size.width / 1.10,
                      height: 50,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        color: const Color(0xFF767680).withOpacity(0.12),
                      ),
                      child: TextFormField(
                        controller: emailController,
                        decoration: InputDecoration(
                            contentPadding: const EdgeInsets.all(8.0),
                            hintText: 'Email',
                            border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(15))),
                        onSaved: (String? value) {},
                        validator: (String? value) {
                          return (value == null || !value.contains('@'))
                              ? 'Email not valid'
                              : null;
                        },
                      ),
                    ),
                    Container(
                      width: MediaQuery.of(context).size.width / 1.10,
                      height: 50,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        color: const Color(0xFF767680).withOpacity(0.12),
                      ),
                      child: TextFormField(
                        controller: passwordController,
                        obscureText: _isObscure,
                        decoration: InputDecoration(
                            suffixIcon: IconButton(
                                icon: Icon(_isObscure
                                    ? Icons.visibility
                                    : Icons.visibility_off),
                                onPressed: () {
                                  setState(() {
                                    _isObscure = !_isObscure;
                                  });
                                }),
                            contentPadding: const EdgeInsets.all(8.0),
                            hintText: 'Contraseña',
                            border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(15),
                                borderSide: const BorderSide(
                                    color: Colors.grey, width: 5.0))),
                        onSaved: (String? value) {},
                        validator: (String? value) {
                          return (value == null || value.isEmpty)
                              ? 'You need to write your password'
                              : null;
                        },
                      ),
                    ),
                    GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            final loginDto = LoginDto(
                                email: emailController.text,
                                password: passwordController.text);
                            BlocProvider.of<LoginBloc>(context)
                                .add(DoLoginEvent(loginDto));
                          }
                        },
                        child: Container(
                            width: MediaQuery.of(context).size.width,
                            margin: const EdgeInsets.only(
                                top: 10, left: 20, right: 20),
                            padding: const EdgeInsets.symmetric(
                                horizontal: 50, vertical: 20),
                            decoration: BoxDecoration(
                                border: Border.all(
                                    color: const Color(0xFFFF3C31)
                                        .withOpacity(0.8),
                                    width: 2),
                                color: const Color(0xFFFF3C31),
                                borderRadius: BorderRadius.circular(15)),
                            child: const Text(
                              'Iniciar Sesion',
                              style: TextStyle(
                                  fontSize: 18,
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold),
                              textAlign: TextAlign.center,
                            ))),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text(
                          '¿No tienes cuenta?',
                          style: TextStyle(fontFamily: 'Helvetica'),
                        ),
                        InkWell(
                          onTap: () =>
                              Navigator.pushNamed(context, '/register'),
                          child: Container(
                            margin: const EdgeInsets.only(left: 10.0),
                            child: const Text(
                              'Registrarme',
                              style: TextStyle(
                                  color: Colors.blue, fontFamily: 'Helvetica'),
                            ),
                          ),
                        )
                      ],
                    ),
                  ],
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
