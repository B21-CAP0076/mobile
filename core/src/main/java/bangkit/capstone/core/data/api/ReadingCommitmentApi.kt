package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.ReadingCommitmentCreate
import bangkit.capstone.core.data.model.ReadingCommitmentUpdate
import retrofit2.http.*

interface ReadingCommitmentApi {
    @GET("/reading_commitment/")
    suspend fun getAll(
        @Query("page") page: Int = 1,
        @Query("owner_id") ownerId: String? = null,
        @Query("partner_id") partnerId: String? = null,
        @Query("owner_reading_cluster") ownerReadingCluster: Int? = null
    ): List<ReadingCommitment>

    @GET("/reading_commitment/{id}")
    suspend fun get(
        @Path("id") id: String,
    ): ReadingCommitment

    @PUT("/reading_commitment/")
    suspend fun create(
        @Body readingCommitmentCreate: ReadingCommitmentCreate
    ): ReadingCommitment

    @PATCH("/reading_commitment/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body readingCommitmentUpdate: ReadingCommitmentUpdate
    ): ReadingCommitment

    @DELETE("/reading_commitment/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): ReadingCommitment

}