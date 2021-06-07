package bangkit.capstone.core.data.model

import java.util.*


// GET
data class ReadingCommitment(
    val id: String,
    val owner: User,
    var partner: User? = null,
    val creation_date: Date,
    val end_date: Date,
    val owner_reading_cluster: Int? = null,
    val book: Book
)

// PUT
data class ReadingCommitmentCreate(
    val end_date: Date,
    val book: Book
)