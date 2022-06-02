import 'package:equatable/equatable.dart';
import 'package:image_picker/image_picker.dart';

abstract class ImagePickBlocEvent extends Equatable {
  const ImagePickBlocEvent();

  @override
  List<Object> get props => [];
}

class SelectImageEvent extends ImagePickBlocEvent {
  final ImageSource source;

  const SelectImageEvent(this.source);

  @override
  List<Object> get props => [source];
}