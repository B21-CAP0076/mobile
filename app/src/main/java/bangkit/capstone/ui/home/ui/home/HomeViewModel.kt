package bangkit.capstone.ui.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.Commitment
import bangkit.capstone.data.ReadingCommitment
import bangkit.capstone.dummy.ProvideDummy
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _commitmentList = MutableLiveData<List<ReadingCommitment>>()
    val commitmentList: LiveData<List<ReadingCommitment>> = _commitmentList
    // book move to paging 3, handle later

    fun getCommitment() {
        viewModelScope.launch {
            _commitmentList.value = ProvideDummy.commitmentList
        }
    }
}