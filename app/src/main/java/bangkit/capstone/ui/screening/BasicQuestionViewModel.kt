package bangkit.capstone.ui.screening

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.BasicQuestion
import bangkit.capstone.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BasicQuestionViewModel : ViewModel() {

    companion object {
        private const val TAG = "BasicQuestionViewModel"
    }

    private val _job = MutableStateFlow("")
    private val _age = MutableStateFlow(0)
    private val _education = MutableStateFlow("")
    val isSubmitEnabled: Flow<Boolean> = combine(_job, _age, _education) { j, a, e ->
        val isJobValid = Constants.JOB_LIST.contains(j)
        val isAgeValid = a in 1..101
        val isEducationValid = Constants.EDUCATION_LIST.contains(e)
        return@combine isJobValid and isAgeValid and isEducationValid
    }

    fun setJob(text: String) {
        viewModelScope.launch {
            _job.value = text
        }
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
            Log.d(
                TAG,
                "Submit ${
                    BasicQuestion(
                        job = _job.value,
                        education = _education.value,
                        age = _age.value
                    )
                }"
            )
        }
    }
}