package bangkit.capstone.data

data class Commitment(
    val id: Int,
    val hobby: String,
    val user1: ReadingCommitment,
    val user2: ReadingCommitment,
    val deadline: Int
)
