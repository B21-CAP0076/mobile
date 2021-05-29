package bangkit.capstone.ui.home.ui.match_making

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bangkit.capstone.data.Match
import bangkit.capstone.dummy.ProvideDummy

class MatchMakingViewModel : ViewModel() {

    private var _matchList = MutableLiveData<List<Match>>()
    var matchList : LiveData<List<Match>> = _matchList

    // TODO: Implement the ViewModel

    fun getMatchList() {
        _matchList.postValue(ProvideDummy.matchList)
    }
}