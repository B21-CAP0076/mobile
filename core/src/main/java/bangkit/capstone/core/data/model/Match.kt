package bangkit.capstone.core.data.model

import bangkit.capstone.core.data.choice.MatchStatus


// GET
data class Match(
    val id: String,
    val commitment_1: ReadingCommitment,
    val commitment_2: ReadingCommitment,
    val status: MatchStatus
)
