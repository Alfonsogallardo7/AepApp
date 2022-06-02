import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_bloc.dart';
import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_event.dart';
import 'package:aep_app/bloc/campeonatos_bloc/campeonatos_state.dart';
import 'package:aep_app/models/campeonatos_response.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository.dart';
import 'package:aep_app/repository/campeonato_repository/campeonato_repository_impl.dart';
import 'package:aep_app/ui/screen/error_screen.dart';
import 'package:aep_app/ui/screen/menu_screen.dart';
import 'package:aep_app/ui/utils/color_style.dart';
import 'package:aep_app/ui/widgets/aep_bar.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({ Key? key }) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
    late CampeonatoRepository campeonatoRepository;
  bool isLikeAnimation = false;
   @override
  void initState() {
    super.initState();
    campeonatoRepository = CampeonatoRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
return BlocProvider(
          create: (context) {
            return CampeonatosBloc(campeonatoRepository)..add(FetchCampeonatosWithType());
          },
          child: _createList(context),
        );
  }

  Widget _createList(BuildContext context) {
    return BlocBuilder<CampeonatosBloc, CampeonatosState>(
      builder: (context, state) {
        if (state is CampeonatosInitial) {
          return const Center(child: CircularProgressIndicator());
        } else if (state is CampeonatosFetchError) {
          return ErrorScreen(
            message: state.message,
            retry: () {
              context.watch<CampeonatosBloc>().add(FetchCampeonatosWithType());
            },
          );
        } else if (state is CampeonatosFetched) {
          return _createPopularView(context, state.campeonatos);
        } else {
          return const Text('Not support');
        }
      },
    );
  }

  Widget _createPopularView(BuildContext context, List<Campeonatos> campeonatos) {
    //final contentHeight = 4.0 * (MediaQuery.of(context).size.width / 2.4) / 3;
    return Column(
      children: [
        SizedBox(
          height: MediaQuery.of(context).size.height / 1.33,
          child: ListView.separated(
            itemBuilder: (BuildContext context, int index) {
              return _createPostViewItem(context, campeonatos[index]);
            },
            scrollDirection: Axis.vertical,
            separatorBuilder: (context, index) => const VerticalDivider(
              color: Colors.transparent,
              width: 6.0,
            ),
            itemCount: campeonatos.length,
          ),
        ),
      ],
    );
  }

  Widget _createPostViewItem(BuildContext context, Campeonatos campeonatos) {
    final width = MediaQuery.of(context).size.width / 2.6;
    return Container(
      color: Colors.white,
      padding: EdgeInsets.symmetric(vertical: 10),
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.symmetric(vertical: 4, horizontal: 16)
                .copyWith(right: 0),
            child: Row(
              children: [
                // CircleAvatar(
                //   radius: 18,
                //   backgroundImage: NetworkImage(campeonatos.fotoUsuario.replaceAll(
                //       'http://localhost:8080/', 'http://10.0.2.2:8080/')),
                // ),
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.only(
                      left: 10,
                    ),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          campeonatos.nombre,
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                          ),
                        )
                      ],
                    ),
                  ),
                ),
                IconButton(
                  onPressed: () {
                    showDialog(
                      context: context,
                      builder: (context) => Dialog(
                        child: ListView(
                          padding: EdgeInsets.symmetric(vertical: 16),
                          shrinkWrap: true,
                          children: ['Delete', 'report']
                              .map((e) => InkWell(
                                    child: Container(
                                      padding: EdgeInsets.symmetric(
                                          vertical: 12, horizontal: 16),
                                      child: Text(e),
                                    ),
                                    onTap: () {},
                                  ))
                              .toList(),
                        ),
                      ),
                    );
                  },
                  icon: Icon(
                    Icons.more_vert,
                  ),
                ),
              ],
            ),
            //
            // image section
          ),
          GestureDetector(
            /*onDoubleTap: () async{
              await FirestoreMethods().likePost(
                widget.snap['postId'],
                user.uid,
                widget.snap['likes'],
              );
              setState(() {
                print(widget.snap['postId']);
                isLikeAnimation = true;
              });
            },*/
            child: Stack(
              alignment: Alignment.center,
              children: [
                SizedBox(
                  height: MediaQuery.of(context).size.height * 0.4,
                  width: double.infinity,
                  child: CachedNetworkImage(
                    placeholder: (context, url) => const Center(
                      child: CircularProgressIndicator(),
                    ),
                    imageUrl: campeonatos.cartel.replaceAll(
                        'http://localhost:8080/', 'http://10.0.2.2:8080/'),
                    fit: BoxFit.cover,
                  ),
                ),
                // AnimatedOpacity(
                //   duration: const Duration(milliseconds: 200),
                //   opacity: isLikeAnimation ? 1 : 0,
                //   child: LikeAnimation(
                //     isAnimation: isLikeAnimation,
                //     child: const Icon(Icons.favorite,
                //         color: Colors.redAccent, size: 100),
                //     duration: const Duration(milliseconds: 400),
                //     onEnd: () {
                //       setState(() {
                //         isLikeAnimation = false;
                //       });
                //     },
                //   ),
                // )
              ],
            ),
          ),
//
//  like and comment section
          Row(
            children: [
              IconButton(
                onPressed: () {},
                icon: Icon(
                  Icons.favorite,
                  color: Colors.red,
                ),
              ),
              IconButton(
                onPressed: () {},
                icon: Icon(
                  Icons.comment_outlined,
                ),
              ),
              IconButton(
                onPressed: () {},
                icon: Icon(
                  Icons.send,
                ),
              ),
              Expanded(
                child: Align(
                  alignment: Alignment.bottomRight,
                  child: IconButton(
                    onPressed: () {},
                    icon: Icon(
                      Icons.bookmark_border,
                    ),
                  ),
                ),
              ),
            ],
          ),
          //
          //  description
          Container(
            padding: const EdgeInsets.symmetric(
              horizontal: 16,
            ),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                DefaultTextStyle(
                  style: Theme.of(context).textTheme.subtitle2!.copyWith(),
                  child: Text(' 4 likes',
                      style: Theme.of(context).textTheme.bodyText2),
                ),
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.only(top: 8),
                  child: RichText(
                    text: TextSpan(
                        style: TextStyle(
                          color: primaryColor,
                        ),
                        children: [
                          TextSpan(
                            text: campeonatos.nombre,
                            style: TextStyle(
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          TextSpan(
                            text: ' ${campeonatos.localidad}',
                          ),
                        ]),
                  ),
                ),
                InkWell(
                  onTap: () {},
                  child: Container(
                    padding: EdgeInsets.symmetric(vertical: 4),
                    child: Text(
                      'View all the 200 comments',
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.blue,
                      ),
                    ),
                  ),
                ),
                /*Container(
                  padding: EdgeInsets.symmetric(vertical: 4),
                  child: Text(
                    /*DateFormat.yMMMd()
                        .format(widget.snap['datePublished'].toDate())*/
                        '2021/30/06',
                    style: TextStyle(
                      fontSize: 12,
                      color: secondaryColor,
                    ),
                  ),
                ),*/
              ],
            ),
          ),
        ],
      ),
    );
  }
}