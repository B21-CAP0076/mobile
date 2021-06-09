package bangkit.capstone.ui.screening

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.data.choice.Education
import bangkit.capstone.core.data.model.UserUpdate
import bangkit.capstone.core.repository.UserRepository
import bangkit.capstone.util.Constants
import bangkit.capstone.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BasicQuestionViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "BasicQuestionViewModel"
    }

    private val _age = MutableStateFlow(0)
    private val _education = MutableStateFlow("")
    private val _status = MutableLiveData<State<String>>()
    val status : LiveData<State<String>> = _status
    val isSubmitEnabled: Flow<Boolean> = combine(_age, _education) { a, e ->
        val isAgeValid = a in 1..101
        val isEducationValid = Constants.EDUCATION_LIST.map {it -> it.name}.contains(e)
        return@combine isAgeValid and isEducationValid
    }


    fun setAge(number: Int) {
        viewModelScope.launch {
            _age.value = number
        }
    }

    fun setEducation(edu: String) {
        viewModelScope.launch {
            _education.value = edu
        }
    }

    fun submit() {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                userRepository.update(UserUpdate(age = _age.value, education = Education.valueOf(_education.value)))
                _status.value = State.success("Your data has been recorded")
            } catch (e : Exception) {
                _status.value = State.failed(e)
            }
        }
    }
}