import 'package:aep_app/bloc/image_pick_bloc/image_pick_bloc.dart';
import 'package:aep_app/bloc/image_pick_bloc/image_pick_event.dart';
import 'package:aep_app/bloc/image_pick_bloc/image_pick_state.dart';
import 'dart:io';
import 'package:datetime_picker_formfield/datetime_picker_formfield.dart';
import 'package:aep_app/bloc/register_bloc/register_bloc.dart';
import 'package:aep_app/bloc/register_bloc/register_event.dart';
import 'package:aep_app/bloc/register_bloc/register_state.dart';
import 'package:aep_app/models/register_dto.dart';
import 'package:aep_app/repository/auth_repository/auth_repository.dart';
import 'package:aep_app/repository/auth_repository/auth_repository_impl.dart';
import 'package:aep_app/repository/constants.dart';
import 'package:aep_app/ui/screen/login_screen.dart';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_picker/image_picker.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  late AuthRepository authRepository;
  bool _isObscure = true;
  final _formKey = GlobalKey<FormState>();
  TextEditingController emailController = TextEditingController();
  TextEditingController nameController = TextEditingController();
  TextEditingController lastNameController = TextEditingController();
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController password2Controller = TextEditingController();
  TextEditingController phoneController = TextEditingController();
  TextEditingController addressController = TextEditingController();
  TextEditingController cityController = TextEditingController();
  TextEditingController postalCodeController = TextEditingController();
  TextEditingController dniController = TextEditingController();
  TextEditingController birthdateController = TextEditingController();
  bool isPublic = true;
  String path = '';
  late SharedPreferences _prefs;
  final format = DateFormat("yyyy-MM-dd");

  @override
  void initState() {
    super.initState();
    init();
    authRepository = AuthrepositoryImpl();
  }

  void init() async {
    _prefs = await SharedPreferences.getInstance();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider(create: (context) {
          return RegisterBloc(authRepository);
        }),
        BlocProvider(create: (context) {
          return ImagePickBlocBloc();
        })
      ],
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        body: _createBody(context),
        backgroundColor: Colors.white,
      ),
    );
  }

  Widget _createBody(BuildContext context) {
    return Container(
      margin: MediaQuery.of(context).padding,
      child: Center(
        child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: BlocConsumer<RegisterBloc, RegisterState>(
              listenWhen: (context, state) {
                return state is RegisterSuccessState ||
                    state is RegisterErrorState;
              },
              listener: (context, state) {
                if (state is RegisterSuccessState) {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const LoginScreen()));
                } else if (state is RegisterErrorState) {
                  _showSnackbar(context, state.message);
                }
              },
              buildWhen: (context, state) {
                return state is RegisterInitialState ||
                    state is RegisterLoadingState;
              },
              builder: (context, state) {
                if (state is RegisterInitialState) {
                  return _buildForm(context);
                } else if (state is RegisterLoadingState) {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                } else {
                  return _buildForm(context);
                }
              },
            )),
      ),
    );
  }

  void _showSnackbar(BuildContext context, String message) {
    final snackBar = SnackBar(
      content: Text(message),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  Widget _buildForm(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Image.asset(
            'assets/images/logo.jpeg',
            width: 254,
            height: 129,
          ),
          Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                  padding: EdgeInsets.only(top: 20, left: 10, bottom: 20),
                  child: const Text(
                    'Registrarme',
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 22),
                  )),
              SizedBox(
                height: 1100,
                child: Form(
                  key: _formKey,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      BlocConsumer<ImagePickBlocBloc, ImagePickBlocState>(
                        listenWhen: (context, state) {
                          return state is ImageSelectedSuccessState;
                        },
                        listener: (context, state) {},
                        buildWhen: (context, state) {
                          return state is ImagePickBlocInitial ||
                              state is ImageSelectedSuccessState;
                        },
                        builder: (context, state) {
                          if (state is ImageSelectedSuccessState) {
                            path = state.pickedFile.path;
                            return Column(
                              children: [
                                ClipRRect(
                                  borderRadius: BorderRadius.circular(100),
                                  child: Image.file(
                                    File(state.pickedFile.path),
                                    width: 150,
                                    height: 150,
                                    fit: BoxFit.fill,
                                  ),
                                ),
                                ElevatedButton(
                                    onPressed: () {
                                      _prefs.setString(
                                          'file', path);
                                    },
                                    child: const Text('Subir imagen'))
                              ],
                            );
                          }
                          return Center(
                            child: Container(
                              padding: EdgeInsets.only(bottom: 20),
                              child: InkWell(
                                onTap: () {
                                  BlocProvider.of<ImagePickBlocBloc>(context)
                                      .add(const SelectImageEvent(
                                          ImageSource.gallery));
                                },
                                child: Image.asset(
                                  'assets/images/mock-avatar.png',
                                  width: 150,
                                ),
                              ),
                            ),
                          );
                        },
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: nameController,
                          decoration: InputDecoration(
                              contentPadding: EdgeInsets.all(8.0),
                              hintText: 'Nombre',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your name'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: lastNameController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Apellidos',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your last name'
                                : null;
                          },
                        ),
                      ),
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
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: usernameController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Username',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: dniController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Dni',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
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
                              contentPadding: EdgeInsets.all(8.0),
                              hintText: 'Contraseña',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your password'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: password2Controller,
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
                              contentPadding: EdgeInsets.all(8.0),
                              hintText: 'Repita su contraseña',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write again your password'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: addressController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Direccion',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: cityController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Localidad',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: postalCodeController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Código postal',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: TextFormField(
                          controller: phoneController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Telefono',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onSaved: (String? value) {},
                          validator: (String? value) {
                            return (value == null || value.isEmpty)
                                ? 'You need to write your nick'
                                : null;
                          },
                        ),
                      ),
                      Container(
                        alignment: Alignment.center,
                        width: MediaQuery.of(context).size.width / 1.10,
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15),
                          color: const Color(0xFF767680).withOpacity(0.12),
                        ),
                        child: DateTimeField(
                          format: format,
                          onSaved: (DateTime? value) {},
                          validator: (DateTime? value) {
                            return (value == null)
                                ? 'You need to write your birthdate'
                                : null;
                          },
                          controller: birthdateController,
                          decoration: InputDecoration(
                              contentPadding: const EdgeInsets.all(8.0),
                              hintText: 'Fecha de nacimiento',
                              border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(15))),
                          onShowPicker: (context, currentValue) {
                            return showDatePicker(
                                context: context,
                                firstDate: DateTime(1900),
                                initialDate: currentValue ?? DateTime.now(),
                                lastDate: DateTime(2100));
                          },
                        ),
                      ),
                      GestureDetector(
                          onTap: () {
                            if (_formKey.currentState!.validate()) {
                              final registerDto = RegisterDto(
                                  username: usernameController.text,
                                  email: emailController.text,
                                  nombre: nameController.text,
                                  apellidos: lastNameController.text,
                                  fechaNacimiento: birthdateController.text,
                                  password: passwordController.text,
                                  password2: password2Controller.text,
                                  dni: dniController.text,
                                  direccion: addressController.text,
                                  localidad: cityController.text,
                                  codigoPostal: postalCodeController.text,
                                  telefono: phoneController.text
                                  );
                              BlocProvider.of<RegisterBloc>(context).add(
                                  DoRegisterEvent(
                                      registerDto,
                                      path
                                      /*_prefs
                                      .getString(Constant.foto_perfil_path)*/));
                            }
                          },
                          child: Container(
                              width: MediaQuery.of(context).size.width,
                              margin: const EdgeInsets.only(
                                  top: 20, left: 30, right: 30, bottom: 10),
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
                                'Registrarme',
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
                            '¿Ya tienes una cuenta?',
                            style: TextStyle(fontFamily: 'Helvetica'),
                          ),
                          InkWell(
                            onTap: () => Navigator.pushNamed(context, '/login'),
                            child: Container(
                              margin: const EdgeInsets.only(left: 10.0),
                              child: const Text(
                                'Inicia sesión',
                                style: TextStyle(
                                    color: Colors.blue,
                                    fontFamily: 'Helvetica'),
                              ),
                            ),
                          )
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
          Container(
            margin: const EdgeInsets.symmetric(vertical: 10.0),
            child: const Text(
              'Al registarte, aceptas nuestros Términos, Política de Datos y Política de Cookies.',
              style: TextStyle(
                  fontFamily: 'Helvetica', color: Colors.grey, fontSize: 10),
              textAlign: TextAlign.center,
            ),
          ),
        ],
      ),
    );
  }
}
