import 'package:flutter/material.dart';

class RecordScreen extends StatefulWidget {
  const RecordScreen({ Key? key }) : super(key: key);

  @override
  State<RecordScreen> createState() => _RecordScreenState();
}

class _RecordScreenState extends State<RecordScreen> {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      child: Text('Lo sentimos, esta sección aún no esta disponible'),
    );
  }
}