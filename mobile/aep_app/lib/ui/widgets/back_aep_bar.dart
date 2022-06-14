import 'package:aep_app/ui/screen/home_screen.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class BackAepBar extends StatefulWidget implements PreferredSizeWidget {
  const BackAepBar({Key? key}) : super(key: key);

  @override
  State<BackAepBar> createState() => _BackAepBarState();

  @override
  // TODO: implement preferredSize
  Size get preferredSize => const Size.fromHeight(100);
}

class _BackAepBarState extends State<BackAepBar> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      margin: const EdgeInsets.only(top: 40),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.only(
                top: 8.0,
                left: 8.0,
                right: MediaQuery.of(context).size.width * 0.26),
            child: GestureDetector(
              onTap: () {
                Navigator.pop(context);
              },
              child: const Icon(CupertinoIcons.chevron_left,
                  color: Color(0xFF939397), size: 25),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const MenuScreen()));
              },
              child: Image.asset(
                'assets/images/logo.jpeg',
                height: 65,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
