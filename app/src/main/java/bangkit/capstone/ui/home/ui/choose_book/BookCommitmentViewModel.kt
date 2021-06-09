package bangkit.capstone.ui.home.ui.choose_book

import androidx.lifecycle.*
import androidx.paging.cachedIn
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.ReadingCommitmentCreate
import bangkit.capstone.core.repository.BookRepository
import bangkit.capstone.core.repository.ReadingCommitmentRepository
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookCommitmentViewModel @Inject constructor(val commitmentRepository: ReadingCommitmentRepository, val bookRepository: BookRepository) :
    ViewModel() {
    val query = MutableStateFlow<String>("")
    val books = query.debounce(300).distinctUntilChanged().mapLatest {
        if (it.trim().isNotEmpty()) {
            bookRepository.getAll(it)
        } else {
            bookRepository.getAll(null)
        }
    }.flattenMerge().asLiveData().cachedIn(viewModelScope)
    private val _status = MutableLiveData<State<String>>()
    val status : LiveData<State<String>> = _status
    val _bookSelected = MutableStateFlow<Book?>(null)

    fun setBook(book: Book) {
        viewModelScope.launch {
            _bookSelected.value = book
        }
    }

    fun makeCommitment(date: Date) {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                val commitment = commitmentRepository.create(ReadingCommitmentCreate(book = _bookSelected.value!!, end_date = date)).single()
                _status.value = State.success(commitment.id)
            } catch (e: Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}