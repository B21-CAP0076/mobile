package bangkit.capstone.ui.screening

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.Genre
import bangkit.capstone.dummy.ProvideDummy
import kotlinx.coroutines.launch

class ChooseGenreViewModel : ViewModel() {

    private val _selectedGenre = mutableListOf<Genre>()
    private val selectedGenre = MutableLiveData<MutableList<Genre>>()
    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList : LiveData<List<Genre>> = _genreList

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

    fun getGenre() {
        viewModelScope.launch {
            _genreList.value = ProvideDummy.genreList
        }
    }

    fun submit() {

    }
}