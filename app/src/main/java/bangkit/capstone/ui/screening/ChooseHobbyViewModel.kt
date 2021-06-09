package bangkit.capstone.ui.screening

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.choice.Education
import bangkit.capstone.core.data.model.Hobby
import bangkit.capstone.core.data.model.UserUpdate
import bangkit.capstone.core.repository.HobbyRepository
import bangkit.capstone.core.repository.UserRepository
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChooseHobbyViewModel @Inject constructor(val userRepository: UserRepository, val hobbyRepository: HobbyRepository): ViewModel() {
    private val _selectedHobby = mutableListOf<Hobby>()
    private val selectedHobby = MutableLiveData<MutableList<Hobby>>()
    private val _hobbyList = MutableLiveData<State<List<Hobby>>>()
    val hobbyList: LiveData<State<List<Hobby>>> = _hobbyList
    private val _status = MutableLiveData<State<String>>()
    val status : LiveData<State<String>> = _status

    init {
        viewModelScope.launch {
            selectedHobby.value = _selectedHobby
        }
    }

    companion object {
        private const val TAG = "ChooseHobbyViewModel"
    }

    fun getHobbyList() {
        viewModelScope.launch {
            try {
                _hobbyList.value = State.loading()
                val lst = hobbyRepository.getAll(null).single()
                _hobbyList.value = State.success(lst)
            } catch (e: Exception) {
                _hobbyList.value = State.failed(e)
            }
        }
    }

    fun setHobby(hobby: Hobby, isSelected: Boolean) {
        viewModelScope.launch {
            if (isSelected) {
                _selectedHobby.add(hobby)
                selectedHobby.value = _selectedHobby
            } else {
                _selectedHobby.remove(hobby)
                selectedHobby.value = _selectedHobby
            }
        }
    }

    fun submit() {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                userRepository.update(UserUpdate(hobbies = selectedHobby.value))
                _status.value = State.success("Your data has been recorded")
            } catch (e : Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}