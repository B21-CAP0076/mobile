package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.data.model.UserUpdate
import retrofit2.http.*

interface UserApi {
    @GET("/user/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("reading_cluster") readingCluster: Int? = null
    ): List<User>

    @GET("/user/{id}")
    suspend fun get(
        @Path("id") id: String
    ): User

    @PATCH("/user/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body userUpdate: UserUpdate
    ): User

    @DELETE("/user/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): User

}