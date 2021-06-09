package bangkit.capstone.ui.home.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.repository.MatchRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CommitmentListViewModel @Inject constructor(val matchRepository: MatchRepository): ViewModel() {

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