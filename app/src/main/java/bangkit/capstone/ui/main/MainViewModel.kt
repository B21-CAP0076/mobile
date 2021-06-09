package bangkit.capstone.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bangkit.capstone.core.repository.UserRepository
import bangkit.capstone.core.util.HeaderInterceptor
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.util.State
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferenceHelper,
    private val interceptor: HeaderInterceptor
) : ViewModel() {

    private val _status = MutableLiveData<State<String>>()
    val status: LiveData<State<String>> = _status

    fun signIn(idToken: String) {
        viewModelScope.launch {
            try {
                _status.value = State.loading()
                val token = userRepository.gauthSwapToken(idToken).single()
                sharedPreferences.setString("token", "${token.token_type} ${token.access_token}")
                interceptor.setSessionToken("${token.token_type} ${token.access_token}")
                val user = userRepository.me().single()
                _status.value = State.success("Success getting user data")
                sharedPreferences.setString("user", Gson().toJson(user))
            } catch (e: Exception) {
                _status.value = State.failed(e)
            }
        }
    }

    fun alreadySignedInBefore() {
        viewModelScope.launch {
            _status.value = State.loading()
            val token = sharedPreferences.getString("token")
            interceptor.setSessionToken(token)
            _status.value = State.success("Success init data")
        }
    }

}