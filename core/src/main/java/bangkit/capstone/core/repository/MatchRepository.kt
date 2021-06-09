package bangkit.capstone.core.repository

import bangkit.capstone.core.data.api.MatchApi
import bangkit.capstone.core.data.choice.MatchAction
import bangkit.capstone.core.data.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val api: MatchApi
){
    suspend fun createMatch(id1: String, id2: String, action: MatchAction) : Flow<Match> {
        return flowOf(api.matchmaking(id1, id2, action))
    }

    suspend fun getAllMatch() : Flow<List<Match>> {
        return flowOf(api.getAllUserMatch())
    }
}