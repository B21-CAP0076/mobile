package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.BookSummary
import bangkit.capstone.core.data.model.BookSummaryCreate
import bangkit.capstone.core.data.model.BookSummaryUpdate
import retrofit2.http.*

interface BookSummaryApi {
    @GET("/book_summary/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("reading_commitment_id") readingCommitmentId: String? = null
    ): List<BookSummary>

    @GET("/book_summary/{id}")
    suspend fun get(
        @Path("id") id: String
    ): BookSummary

    @PUT("/book_summary/")
    suspend fun create(
        @Body bookSummaryCreate: BookSummaryCreate
    ): BookSummary

    @PATCH("/book_summary/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body bookSummaryUpdate: BookSummaryUpdate
    ): BookSummary

    @DELETE("/book_summary/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): BookSummary

}