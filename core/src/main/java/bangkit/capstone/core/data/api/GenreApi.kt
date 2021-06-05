package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.Genre
import retrofit2.http.GET
import retrofit2.http.Path

interface GenreApi {
    @GET("/genre/")
    suspend fun getAll(): List<Genre>

    @GET("/genre/{id}")
    suspend fun get(
        @Path("id") id: String
    ) : Genre
}