package bangkit.capstone.ui.home.ui.commitment_room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import bangkit.capstone.core.data.model.BookSummary
import bangkit.capstone.core.data.model.BookSummaryCreate
import bangkit.capstone.core.repository.BookSummaryRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommitmentRoomViewModel @Inject constructor(val summaryRepository: BookSummaryRepository) :
    ViewModel() {

    private val _summaryList = MutableLiveData<PagingData<BookSummary>>()
    val bookSummary: LiveData<PagingData<BookSummary>> = _summaryList
    private val _status = MutableLiveData<State<String>>()
    val status : LiveData<State<String>> = _status

    companion object {
        private const val TAG = "CommitmentRoomViewModel"
    }

    @ExperimentalCoroutinesApi
    fun getSummaries(id1: String, id2: String) {
        viewModelScope.launch {
            summaryRepository.getAllWithinReadingCommitment(id1).cachedIn(viewModelScope).collect {
                _summaryList.postValue(it)
            }
//            summaryRepository.getAllWithinReadingCommitment(id2).cachedIn(viewModelScope).collect {
//                _summaryList.postValue(it)
//            }
        }
    }

    fun sendSummary(id: String, text: String) {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                summaryRepository.create(id, BookSummaryCreate(text))
                _status.value = State.success("Success create summary")
            } catch (e: Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}