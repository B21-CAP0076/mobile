package bangkit.capstone.ui.home.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.ReadingCommitmentCreate
import bangkit.capstone.core.repository.BookRepository
import bangkit.capstone.core.repository.MatchRepository
import bangkit.capstone.core.repository.ReadingCommitmentRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val matchRepository: MatchRepository): ViewModel() {


    private val _commitmentList = MutableLiveData<State<List<Match>>>()
    val commitmentList: LiveData<State<List<Match>>> = _commitmentList


    fun getCommitment() {
        viewModelScope.launch {
            try {
                _commitmentList.value = State.loading()
                val lst = matchRepository.getAllMatch().single()
                _commitmentList.value = State.success(lst)
            } catch (e: Exception) {
                _commitmentList.value = State.failed(e)
            }
        }
    }


}