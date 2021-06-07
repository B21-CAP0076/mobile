package bangkit.capstone.core.data.model

import bangkit.capstone.core.data.choice.MatchStatus
import java.util.*


// GET
data class ReadingCommitment(
    val id: String,
    val owner: User,
    val owner_reading_cluster: Int? = null,
    val creation_date: Date,
    val end_date: Date,
    val book: Book,
    val status: MatchStatus
)

// PUT
data class ReadingCommitmentCreate(
    val end_date: Date,
    val book: Book
)