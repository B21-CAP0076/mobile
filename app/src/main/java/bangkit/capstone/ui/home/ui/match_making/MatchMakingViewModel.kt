package bangkit.capstone.ui.home.ui.match_making

import androidx.lifecycle.*
import bangkit.capstone.core.data.choice.MatchAction
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.repository.MatchRepository
import bangkit.capstone.core.repository.ReadingCommitmentRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchMakingViewModel @Inject constructor(
    private val commitmentRepository: ReadingCommitmentRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {
    private val _status = MutableLiveData<State<String>>()
    val status: LiveData<State<String>> = _status
    private var _matchList = MutableLiveData<List<ReadingCommitment>>()
    var matchList = commitmentRepository.getAllUserPotentialMatch().asLiveData()

    fun match(id1: String, id2: String, action: MatchAction) {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                matchRepository.createMatch(id1, id2, action)
                _status.value = State.success("Success Create Match")
            } catch (e: Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}