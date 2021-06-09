package bangkit.capstone.core.data.api


import bangkit.capstone.core.data.model.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("/book/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("title") title: String? = null
    ): List<Book>

    @GET("/book/{id}")
    suspend fun get(
        @Path("id") id: String
    ): Book

}