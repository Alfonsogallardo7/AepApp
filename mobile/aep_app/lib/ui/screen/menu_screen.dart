import 'package:aep_app/ui/screen/home_screen.dart';
import 'package:aep_app/ui/screen/perfil_screen.dart';
import 'package:aep_app/ui/screen/record_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

class MenuScreen extends StatefulWidget {
  const MenuScreen({Key? key}) : super(key: key);

  @override
  State<MenuScreen> createState() => _MenuScreenState();
}

class _MenuScreenState extends State<MenuScreen> {
  int _selectedIndex = 0;
  static const TextStyle optionStyle =
      TextStyle(fontSize: 30, fontWeight: FontWeight.bold);
  static const List<Widget> pages = <Widget>[
    RecordScreen(),
    HomeScreen(),
    PerfilScreen(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
            margin: MediaQuery.of(context).padding,
            child: pages[_selectedIndex]),
        bottomNavigationBar: _buildBottomBar());
  }

  Widget _buildBottomBar() {
    return Container(
        decoration: const BoxDecoration(
            border: Border(
          top: BorderSide(
            color: Color(0xfff1f1f1),
            width: 1.0,
          ),
        )),
        padding: const EdgeInsets.symmetric(horizontal: 50.0),
        height: 90,
        child: Padding(
          padding: const EdgeInsets.only(bottom: 10.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              GestureDetector(
                child: SvgPicture.asset('assets/images/list-icon.svg',
                    color: _selectedIndex == 0
                        ? const Color(0xffFF635A)
                        : const Color(0xff848488),
                    width: 30),
                onTap: () {
                  setState(() {
                    _selectedIndex = 0;
                  });
                },
              ),
              GestureDetector(
                child: SvgPicture.asset('assets/images/home-icon.svg',
                    color: _selectedIndex == 1
                        ? const Color(0xffFF635A)
                        : const Color(0xff848488),
                    width: 30),
                onTap: () {
                  setState(() {
                    _selectedIndex = 1;
                  });
                },
              ),
              GestureDetector(
                onTap: () {
                  setState(() {
                    _selectedIndex = 2;
                  });
                },
                child: Container(
                  padding: const EdgeInsets.all(5),
                  // decoration: BoxDecoration(
                  //     borderRadius: BorderRadius.circular(100),
                  //     border: Border.all(
                  //         color: _selectedIndex == 2
                  //             ? Colors.black
                  //             : Colors.transparent,
                  //         width: 1)),
                  child: ClipRRect(
                    // child: Icon(Icons.person,
                    //     color: _selectedIndex == 2
                    //         ? const Color(0xffFF635A)
                    //         : const Color(0xff848488),
                    //         size: 30),
                    child: SvgPicture.asset('assets/images/perfil-icon.svg',
                        color: _selectedIndex == 2
                            ? const Color(0xffFF635A)
                            : const Color(0xff848488),
                        width: 30),
                  ),
                ),
              )
            ],
          ),
        ));
  }
}
