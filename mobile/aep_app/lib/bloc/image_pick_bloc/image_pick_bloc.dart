import 'package:aep_app/bloc/image_pick_bloc/image_pick_event.dart';
import 'package:aep_app/bloc/image_pick_bloc/image_pick_state.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_picker/image_picker.dart';

class ImagePickBlocBloc extends Bloc<ImagePickBlocEvent, ImagePickBlocState> {
  ImagePickBlocBloc() : super(ImagePickBlocInitial()) {
    on<SelectImageEvent>(_onSelectImage);
  }

  void _onSelectImage(
      SelectImageEvent event, Emitter<ImagePickBlocState> emit) async {
    final ImagePicker _picker = ImagePicker();

    try {
      final XFile? pickedFile = await _picker.pickImage(
        source: event.source,
      );
      if (pickedFile != null) {
        emit(ImageSelectedSuccessState(pickedFile));
      } else {
        emit(const ImageSelectedErrorState('Error in image selection'));
      }
    } catch (e) {
      emit(const ImageSelectedErrorState('Error in image selection'));
    }
  }
}