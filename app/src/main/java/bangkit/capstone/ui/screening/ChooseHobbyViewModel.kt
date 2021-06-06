package bangkit.capstone.ui.screening

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.Hobby
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChooseHobbyViewModel : ViewModel() {
    private val _selectedHobby = mutableListOf<Hobby>()
    private val selectedHobby = MutableLiveData<MutableList<Hobby>>()

    init {
        viewModelScope.launch {
            selectedHobby.value = _selectedHobby
        }
    }

    companion object {
        private const val TAG = "ChooseHobbyViewModel"
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
            Log.d(TAG, "Submit: ${selectedHobby.value}")
        }
    }
}