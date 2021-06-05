package bangkit.capstone.core.data.api

import bangkit.capstone.core.data.choice.SwipeState
import bangkit.capstone.core.data.model.Swipe
import retrofit2.http.POST
import retrofit2.http.Query


interface SwipeApi {
    @POST("/swipe/")
    suspend fun swipe(
        @Query("commitment_1_id") commitment1Id: String,
        @Query("commitment_2_id") commitment2Id: String,
        @Query("state") state: SwipeState
    ): Swipe

}