package org.gfigueras.practicaobligatorio.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ERES GILIPOLLAS"
    }
    val text: LiveData<String> = _text
}