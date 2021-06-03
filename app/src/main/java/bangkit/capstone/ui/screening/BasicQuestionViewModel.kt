package bangkit.capstone.ui.screening

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.data.BasicQuestion
import kotlinx.coroutines.launch

class BasicQuestionViewModel : ViewModel() {

    companion object {
        private const val TAG = "BasicQuestionViewModel"
    }

    private val _job = MutableLiveData<String>("")
    val job: LiveData<String> = _job
    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> = _age
    private val _education = MutableLiveData<String>()
    val education: LiveData<String> = _education

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
                        job = _job.value!!,
                        education = _education.value!!,
                        age = _age.value!!
                    )
                }"
            )
        }
    }
}