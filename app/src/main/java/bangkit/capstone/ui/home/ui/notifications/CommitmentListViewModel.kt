package bangkit.capstone.ui.home.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.dummy.ProvideDummy
import kotlinx.coroutines.launch

class CommitmentListViewModel : ViewModel() {

    private val _commitmentList = MutableLiveData<List<Match>>()
    val commitmentList: LiveData<List<Match>> = _commitmentList
    // book move to paging 3, handle later

    fun getCommitment() {
        viewModelScope.launch {
            _commitmentList.value = ProvideDummy.matchList
        }
    }
}