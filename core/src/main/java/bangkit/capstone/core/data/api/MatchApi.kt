package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.choice.MatchAction
import bangkit.capstone.core.data.model.Match
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface MatchApi {
    @POST("/match")
    suspend fun matchmaking(
        @Query("commitment_1_id") commitment1Id: String,
        @Query("commitment_2_id") commitment2Id: String,
        @Query("action") action: MatchAction
    ): Match

    @GET("/match/user")
    suspend fun getAllUserMatch(): List<Match>


}