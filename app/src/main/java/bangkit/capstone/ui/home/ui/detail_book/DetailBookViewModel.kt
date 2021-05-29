package bangkit.capstone.ui.home.ui.detail_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bangkit.capstone.data.Book
import bangkit.capstone.dummy.ProvideDummy

class DetailBookViewModel : ViewModel() {
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    fun getDetailBook(id: Int) {
        _book.postValue(ProvideDummy.bookList.first { it -> it.id == id })
    }
}