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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReadingCommitment

        if (id != other.id) return false
        if (owner != other.owner) return false
        if (owner_reading_cluster != other.owner_reading_cluster) return false
        if (creation_date != other.creation_date) return false
        if (end_date != other.end_date) return false
        if (book != other.book) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + (owner_reading_cluster ?: 0)
        result = 31 * result + creation_date.hashCode()
        result = 31 * result + end_date.hashCode()
        result = 31 * result + book.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}

// PUT
data class ReadingCommitmentCreate(
    val end_date: Date,
    val book: Book
)