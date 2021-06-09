package bangkit.capstone.core.repository

import android.util.Log
import bangkit.capstone.core.data.api.UserApi
import bangkit.capstone.core.data.model.Header
import bangkit.capstone.core.data.model.RequestToken
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.data.model.UserUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val api: UserApi
){
    companion object {
        private const val TAG = "UserRepository"
    }
    suspend fun gauthSwapToken(token: String) : Flow<Header> {
        return flowOf(api.gauthSwapToken(token))
    }

    suspend fun me() : Flow<User> {
        return flowOf(api.me())
    }

    suspend fun update(userUpdate: UserUpdate) : Flow<User>  {
        return flowOf(api.update(userUpdate))
    }

    suspend fun delete() : Flow<User> {
        val user = api.delete()
        return flowOf(user)
    }

    suspend fun predictUserCluster() : Flow<User> {
        return flowOf(api.predictUserCluster())
    }

}