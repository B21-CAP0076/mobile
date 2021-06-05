package bangkit.capstone.ui.home.ui.commitment_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.BookSummary
import bangkit.capstone.dummy.ProvideDummy
import kotlinx.coroutines.launch

class CommitmentRoomViewModel : ViewModel() {
    private val _bookSummary = MutableLiveData<BookSummary>()
    val bookSummary : LiveData<BookSummary> = _bookSummary

    fun getCommitment(id: String) {
        viewModelScope.launch {
            _bookSummary.value = ProvideDummy.bookSummaries
        }
    }
}