package bangkit.capstone.ui.home.ui.commitment_room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.BookSummary
import bangkit.capstone.dummy.ProvideDummy
import kotlinx.coroutines.launch

class CommitmentRoomViewModel : ViewModel() {
    private val _bookSummary = MutableLiveData<BookSummary>()
    val bookSummary : LiveData<BookSummary> = _bookSummary

    companion object {
        private const val TAG = "CommitmentRoomViewModel"
    }

    fun getCommitment(id: String) {
        viewModelScope.launch {
            _bookSummary.value = ProvideDummy.bookSummaries
        }
    }

    fun sendSummary(text: String) {
        Log.d(TAG, text)
    }
}