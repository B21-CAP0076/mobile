package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.ReadingCommitmentCreate
import retrofit2.http.*


interface ReadingCommitmentApi {
    @GET("/reading_commitment/user")
    suspend fun getAllUserReadingCommitment(
        @Query("page") page: Int
    ): List<ReadingCommitment>

    @GET("/reading_commitment/potential_match")
    suspend fun getAllUserPotentialMatch(
        @Query("page") page: Int
    ) : List<ReadingCommitment>

    @GET("/reading_commitment/{match_id}/own")
    suspend fun getOwnReadingCommitmentWithinMatch(
        @Path("match_id") matchId: String
    ) : ReadingCommitment

    @GET("/reading_commitment/{match_id}/partner")
    suspend fun getPartnerReadingCommitmentWithinMatch(
        @Path("match_id") matchId: String
    ) : ReadingCommitment

    @PUT("/reading_commitment/create")
    suspend fun create(
        @Body readingCommitmentCreate: ReadingCommitmentCreate
    ): ReadingCommitment

    @DELETE("/reading_commitment/delete/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): ReadingCommitment

}