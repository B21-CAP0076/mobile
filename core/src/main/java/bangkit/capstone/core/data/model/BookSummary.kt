package bangkit.capstone.core.data.model

import java.util.*


// GET
data class BookSummary(
    val id: String,
    val reading_commitment: ReadingCommitment,
    val creation_date: Date,
    var summary: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookSummary

        if (id != other.id) return false
        if (reading_commitment != other.reading_commitment) return false
        if (creation_date != other.creation_date) return false
        if (summary != other.summary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + reading_commitment.hashCode()
        result = 31 * result + creation_date.hashCode()
        result = 31 * result + summary.hashCode()
        return result
    }
}

// PUT
data class BookSummaryCreate(
    val summary: String
)

// PATCH
data class BookSummaryUpdate(
    val summary: String
)
