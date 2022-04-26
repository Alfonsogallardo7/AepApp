import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.grey,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool _isObscure = true;
  late String _myActivity;
  final format = DateFormat("yyyy-MM-dd");

  @override
  void initState() {
    super.initState();
    _myActivity = '';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
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
                child: Form(
                  child: SizedBox(
                    height: 400,
                    child: Form(
                      //key: _formKey,
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Container(
                            width: MediaQuery.of(context).size.width / 1.14,
                            height: 50,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(15),
                              color: Color(0xFF767680).withOpacity(0.12),
                            ),
                            child: TextFormField(
                              //controller: emailController,
                              decoration: InputDecoration(
                                  contentPadding: EdgeInsets.all(8.0),
                                  hintText: 'Role',
                                  border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(15),
                                  )),
                              onSaved: (String? value) {},
                              validator: (String? value) {
                                return (value == null || value.isEmpty)
                                    ? 'You need to write your name'
                                    : null;
                              },
                            ),
                          ),
                          /*Container(
                          width: 200,
                            child: DropDownFormField(
                              titleText: 'My workout',
                              hintText: 'Please choose one',
                              value: _myActivity,
                              onSaved: (value) {
                                setState(() {
                                  _myActivity = value;
                                });
                              },
                              onChanged: (value) {
                                setState(() {
                                  _myActivity = value;
                                });
                              },
                              dataSource: [
                                {
                                  "display": "Running",
                                  "value": "Running",
                                },
                                {
                                  "display": "Climbing",
                                  "value": "Climbing",
                                },
                                {
                                  "display": "Walking",
                                  "value": "Walking",
                                },
                                {
                                  "display": "Swimming",
                                  "value": "Swimming",
                                },
                                {
                                  "display": "Soccer Practice",
                                  "value": "Soccer Practice",
                                },
                                {
                                  "display": "Baseball Practice",
                                  "value": "Baseball Practice",
                                },
                                {
                                  "display": "Football Practice",
                                  "value": "Football Practice",
                                },
                              ],
                              textField: 'display',
                              valueField: 'value',
                            ),
                          ),*/
                          Container(
                            width: MediaQuery.of(context).size.width / 1.14,
                            height: 50,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(15),
                              color: Color(0xFF767680).withOpacity(0.12),
                            ),
                            child: TextFormField(
                              //controller: emailController,
                              decoration: InputDecoration(
                                  contentPadding: EdgeInsets.all(8.0),
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
                            width: MediaQuery.of(context).size.width / 1.14,
                            height: 50,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(15),
                              color: Color(0xFF767680).withOpacity(0.12),
                            ),
                            child: TextFormField(
                              //controller: passwordController,
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
                                      borderRadius: BorderRadius.circular(15),
                                      borderSide: BorderSide(
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
                                /*if (_formKey.currentState!.validate()) {
                              final registerDto = RegisterDto(
                                  username: usernameController.text,
                                  email: emailController.text,
                                  nombre: nameController.text,
                                  apellidos: lastNameController.text,
                                  fechaNacimiento: birthdateController.text,
                                  password: passwordController.text,
                                  password2: password2Controller.text);
                              BlocProvider.of<RegisterBloc>(context).add(
                                  DoRegisterEvent(
                                      registerDto,
                                      _prefs
                                          .getString(Constant.foto_perfil_path)!));
                            }*/
                              },
                              child: Container(
                                  width: MediaQuery.of(context).size.width,
                                  margin: const EdgeInsets.only(
                                      top: 10, left: 20, right: 20),
                                  padding: const EdgeInsets.symmetric(
                                      horizontal: 50, vertical: 20),
                                  decoration: BoxDecoration(
                                      border: Border.all(
                                          color: Color(0xFFFF3C31)
                                              .withOpacity(0.8),
                                          width: 2),
                                      color: Color(0xFFFF3C31),
                                      borderRadius: BorderRadius.circular(15)),
                                  child: Text(
                                    'Register'.toUpperCase(),
                                    style: const TextStyle(
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
                                onTap: () =>
                                    Navigator.pushNamed(context, '/login'),
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
                ),
              ),
            )
          ],
        ),
      ),
      backgroundColor: Colors.white,
    );
  }
}
