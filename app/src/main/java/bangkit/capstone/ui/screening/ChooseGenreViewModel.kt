package bangkit.capstone.ui.screening

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.model.Genre
import bangkit.capstone.core.data.model.UserUpdate
import bangkit.capstone.core.repository.GenreRepository
import bangkit.capstone.core.repository.UserRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChooseGenreViewModel @Inject constructor(val genreRepository: GenreRepository, val userRepository: UserRepository): ViewModel() {

    private val _selectedGenre = mutableListOf<Genre>()
    private val selectedGenre = MutableLiveData<MutableList<Genre>>()
    private val _genreList = MutableLiveData<State<List<Genre>>>()
    val genreList : LiveData<State<List<Genre>>> = _genreList
    private val _status = MutableLiveData<State<String>>()
    val status : LiveData<State<String>> = _status

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
            try {
                _genreList.value = State.loading()
                val lst = genreRepository.getAll(null).single()
                _genreList.value = State.success(lst)
            } catch (e: Exception) {
                _genreList.value = State.failed(e)
            }
        }
    }

    fun submit() {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                userRepository.update(UserUpdate(genre_preferences = selectedGenre.value))
                _status.value = State.success("Your data has been recorded")
            } catch (e : Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}