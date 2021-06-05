package bangkit.capstone.core.data.model

import java.util.*


// GET
data class ReadingCommitment(
    val id: String,
    val owner: User,
    var partner: User? = null,
    val creation_date: Date,
    val end_date: Date,
    val book: Book
)

// PUT
data class ReadingCommitmentCreate(
    val owner: User,
    var partner: User? = null,
    val creation_date: Date,
    val end_date: Date,
    val book: Book
)

// PATCH
data class ReadingCommitmentUpdate(
    val partner: User? = null
)