import 'package:aep_app/ui/screen/home_screen.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';
import 'package:flutter/material.dart';

class AepBar extends StatefulWidget implements PreferredSizeWidget {
  const AepBar({Key? key}) : super(key: key);

  @override
  State<AepBar> createState() => _AepBarState();

  @override
  // TODO: implement preferredSize
  Size get preferredSize => const Size.fromHeight(100);
}

class _AepBarState extends State<AepBar> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      margin: const EdgeInsets.only(top: 10),
      child: Expanded(
          flex: 1,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
               Padding(
                padding: const EdgeInsets.all(8.0),
                child: GestureDetector(
                            onTap: () {Navigator.push(context, MaterialPageRoute(builder: (context) => const MenuScreen()));},
                            child: Image.asset('assets/images/logo.jpeg',height: 65,),
                          ), 
              ),
            ],
          )),
    );
  }
}
