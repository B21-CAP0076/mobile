package bangkit.capstone.ui.screening

import androidx.lifecycle.*
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.UserUpdate
import bangkit.capstone.core.repository.BookRepository
import bangkit.capstone.core.repository.UserRepository
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
@FlowPreview
@ExperimentalCoroutinesApi
class ChooseBookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val userRepository: UserRepository,
    private val sharedPreference: SharedPreferenceHelper
) : ViewModel() {
    private val _selectedBooks = mutableListOf<Book>()
    private val selectedBooks = MutableLiveData<MutableList<Book>>()
    private val _status = MutableLiveData<State<String>>()
    val status: LiveData<State<String>> = _status
    val query = MutableStateFlow<String>("")
    val books = query.debounce(300).distinctUntilChanged().mapLatest {
        if (it.trim().isNotEmpty()) {
            bookRepository.getAll(it)
        } else {
            bookRepository.getAll(null)
        }
    }.flattenMerge().asLiveData()

    init {
        viewModelScope.launch {
            selectedBooks.value = _selectedBooks
        }
    }

    fun setBook(data: Book) {
        viewModelScope.launch {
            if (!_selectedBooks.contains(data)) {
                _selectedBooks.add(data)
                selectedBooks.value = _selectedBooks
            } else {
                _selectedBooks.remove(data)
                selectedBooks.value = _selectedBooks
            }
        }
    }

    fun submit() {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                userRepository.update(UserUpdate(previous_books = selectedBooks.value))
                userRepository.predictUserCluster()
                    sharedPreference.setBool("screening", true)
                    _status.value = State.success("Your data has been recorded")

            } catch (e: Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}