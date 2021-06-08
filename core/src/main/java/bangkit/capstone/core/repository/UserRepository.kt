package bangkit.capstone.core.repository

import bangkit.capstone.core.data.api.UserApi
import bangkit.capstone.core.data.model.Header
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.data.model.UserUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val api: UserApi
) {
    suspend fun gauthSwapToken(token: String): Flow<Header> {
        return flowOf(api.gauthSwapToken(token))
    }

    suspend fun me(): Flow<User> {
        return flowOf(api.me())
    }

    suspend fun update(userUpdate: UserUpdate): Flow<User> {
        val user = api.update(userUpdate)
        return flowOf(user)
    }

    suspend fun delete(): Flow<User> {
        val user = api.delete()
        return flowOf(user)
    }

    suspend fun predictUserCluster(): Flow<User> {
        return flowOf(api.predictUserCluster())
    }

}