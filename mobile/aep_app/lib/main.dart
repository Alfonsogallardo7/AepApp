import 'package:aep_app/ui/screen/login_screen.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'AepApp',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/',
  routes: {
    '/': (context) => const LoginScreen(),
    '/login': (context) => const LoginScreen(),
    //'/register': (context) => const RegisterScreen(),
  },
    );
  }
}