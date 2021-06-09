package bangkit.capstone.core.data.model


// GET
data class Book(
    val id: String,
    val img: String,
    val title: String,
    val authors: List<Author>,
    val genres: List<Genre>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (id != other.id) return false
        if (img != other.img) return false
        if (title != other.title) return false
        if (authors != other.authors) return false
        if (genres != other.genres) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + img.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + authors.hashCode()
        result = 31 * result + genres.hashCode()
        return result
    }
}
