package bangkit.capstone.ui.screening

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class BasicQuestionViewModel : ViewModel() {

    companion object {
        private const val TAG = "BasicQuestionViewModel"
    }

    val jobChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val bioChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val userResponse = combine(jobChannel.asFlow(), bioChannel.asFlow()) {
        job, bio -> BasicQuestion(job= job,  bio = bio)
    }


    fun submit() {
        viewModelScope.launch {
            userResponse.collectLatest {
                Log.d(TAG, it.toString())
            }
        }
    }
}