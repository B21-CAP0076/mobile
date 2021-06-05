package bangkit.capstone.core.data.model

import java.util.*


// GET
data class BookSummary(
    val id: String,
    val reading_commitment: ReadingCommitment,
    val creation_date: Date,
    var summary: String,
)

// PUT
data class BookSummaryCreate(
    val reading_commitment: ReadingCommitment,
    val creation_date: Date,
    val summary: String
)

// PATCH
data class BookSummaryUpdate(
    val creation_date: Date,
    val summary: String
)