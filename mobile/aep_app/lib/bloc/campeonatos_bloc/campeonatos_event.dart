
import 'package:equatable/equatable.dart';

abstract class PostsEvent extends Equatable {
  const PostsEvent();

  @override
  List<Object> get props => [];
}

class FetchPostsWithType extends PostsEvent {


  const FetchPostsWithType();

  @override
  List<Object> get props => [];
}