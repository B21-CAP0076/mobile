package bangkit.capstone.core.data.model


// GET
data class Book(
    val id: String,
    val img: String,
    val title: String,
    val authors: List<Author>,
    val genres: List<Genre>,
)
