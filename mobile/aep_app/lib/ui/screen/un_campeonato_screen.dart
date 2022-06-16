import 'package:aep_app/models/campeonatos_response.dart';
import 'package:aep_app/ui/widgets/aep_bar.dart';
import 'package:aep_app/ui/widgets/back_aep_bar.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';

class UnCampeonatoScreen extends StatefulWidget {
  final Campeonatos unCampeonato;
  const UnCampeonatoScreen(this.unCampeonato,
      // ignore: non_constant_identifier_names
      {Key? key,
      required Campeonatos Campeonato})
      : super(key: key);

  @override
  State<UnCampeonatoScreen> createState() => _UnCampeonatoScreenState();
}

class _UnCampeonatoScreenState extends State<UnCampeonatoScreen> {
  @override
  Widget build(BuildContext context) {
    var campeonatoName = widget.unCampeonato.nombre;
    return Scaffold(
      appBar: const BackAepBar(),
      body: Container(
          alignment: Alignment.center,
          child: Column(
            children: [
              Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.only(top: 20, right: 8.0, left: 8.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(widget.unCampeonato.nombre,
                        style: const TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                            color: Colors.black)),
                  ],
                ),
              ),
              Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.only(top: 20, right: 8.0, left: 8.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Container(
                      padding: const EdgeInsets.all(8.0),
                      height: MediaQuery.of(context).size.height * 0.25,
                      width: MediaQuery.of(context).size.width * 0.40,
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(15),
                        child: CachedNetworkImage(
                          placeholder: (context, url) => const Center(
                            child: CircularProgressIndicator(),
                          ),
                          imageUrl: widget.unCampeonato.cartel,
                          fit: BoxFit.fill,
                        ),
                      ),
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Padding(
                          padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                          child: Text(
                              'Fecha de Inicio: ${widget.unCampeonato.fechaInicio}',
                              style: const TextStyle(
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black)),
                        ),
                        Padding(
                          padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                          child: Text(
                              'Fecha de Fin: ${widget.unCampeonato.fechaFin}',
                              style: const TextStyle(
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black)),
                        ),
                        Padding(
                          padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                          child: Text(
                              'Localidad: ${widget.unCampeonato.localidad} (${widget.unCampeonato.provincia})',
                              style: const TextStyle(
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black)),
                        ),
                        Padding(
                          padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                          child: Text(
                              'Organizador: ${widget.unCampeonato.organizador}',
                              style: const TextStyle(
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black)),
                        ),
                      ],
                    )
                  ],
                ),
              ),
            ],
          )),
      backgroundColor: Colors.white,
    );
  }
}
