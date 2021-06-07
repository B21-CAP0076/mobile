package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.Hobby
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HobbyApi {
    @GET("/hobby/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("name") name: String? = null
    ): List<Hobby>

    @GET("/hobby/{id}")
    suspend fun get(
        @Path("id") id: String
    ): Hobby
}