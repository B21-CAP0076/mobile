package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.Hobby
import retrofit2.http.GET
import retrofit2.http.Path

interface HobbyApi {
    @GET("/hobby/")
    suspend fun getAll(): List<Hobby>

    @GET("/hobby/{id}")
    suspend fun get(
        @Path("id") id: String
    ): Hobby
}