package bangkit.capstone.ui.screening

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.Genre
import kotlinx.coroutines.launch

class ChooseGenreViewModel : ViewModel() {

    private val _selectedGenre = mutableListOf<Genre>()
    private val selectedGenre = MutableLiveData<MutableList<Genre>>()

    init {
        viewModelScope.launch {
            selectedGenre.value = _selectedGenre
        }
    }

    fun setGenre(genre: Genre, selected: Boolean) {
        viewModelScope.launch {
            if (selected) {
                _selectedGenre.add(genre)
                selectedGenre.value = _selectedGenre
            } else {
                _selectedGenre.remove(genre)
                selectedGenre.value = _selectedGenre
            }
        }
    }

    fun submit() {

    }
}