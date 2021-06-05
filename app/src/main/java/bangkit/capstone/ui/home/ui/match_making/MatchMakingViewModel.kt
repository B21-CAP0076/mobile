package bangkit.capstone.ui.home.ui.match_making

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bangkit.capstone.core.data.model.Swipe

class MatchMakingViewModel : ViewModel() {

    private var _matchList = MutableLiveData<List<Swipe>>()
    var swipeList : LiveData<List<Swipe>> = _matchList

    // TODO: Implement the ViewModel

//    fun getMatchList() {
//        _matchList.postValue(ProvideDummy.matchList)
//    }
}