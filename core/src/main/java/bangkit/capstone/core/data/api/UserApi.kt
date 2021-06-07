package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.Header
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.data.model.UserUpdate
import retrofit2.http.*


interface UserApi {
    @GET("/user/gauth/swap_token")
    suspend fun gauthSwapToken(
        @Query("token") token: String
    ): Header

    @GET("/user/me")
    suspend fun me(): User

    @PATCH("/user")
    suspend fun update(
        @Body userUpdate: UserUpdate
    ): User

    @DELETE("/user")
    suspend fun delete(): User

    @PATCH("/user/predict")
    suspend fun predictUserCluster()

}