package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.BookSummary
import bangkit.capstone.core.data.model.BookSummaryCreate
import bangkit.capstone.core.data.model.BookSummaryUpdate
import retrofit2.http.*

interface BookSummaryApi {

    @GET("/book_summary/user/reading_commitment")
    suspend fun getAllWithinReadingCommitment(
        @Query("reading_commitment_id") readingCommitmentId: String,
        @Query("page") page: Int = 1
    ) : List<BookSummary>

    @PUT("/book_summary/create")
    suspend fun create(
        @Query("reading_commitment_id") readingCommitmentId: String,
        @Body bookSummaryCreate: BookSummaryCreate
    ): BookSummary

    @PATCH("/book_summary/update/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body bookSummaryUpdate: BookSummaryUpdate
    ): BookSummary

    @DELETE("/book_summary/delete/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): BookSummary

}