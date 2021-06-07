package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.Genre
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenreApi {
    @GET("/genre/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("") name: String? = null
    ): List<Genre>

    @GET("/genre/{id}")
    suspend fun get(
        @Path("id") id: String
    ) : Genre
}