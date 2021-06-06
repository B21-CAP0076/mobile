package bangkit.capstone.ui.screening

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.Book
import kotlinx.coroutines.launch

class ChooseBookViewModel : ViewModel() {
    private val _selectedBooks = mutableListOf<Book>()
    private val selectedBooks = MutableLiveData<MutableList<Book>>()

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

    fun submit() {}
}