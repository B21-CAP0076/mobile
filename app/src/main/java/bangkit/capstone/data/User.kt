package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("g_id")
    val gId: String? = null,

    @field:SerializedName("birthdate")
    val birthdate: String? = null,

    @field:SerializedName("education")
    val education: String? = null,

    @field:SerializedName("hobbies")
    val hobbies: List<Hobby?>? = null,

    @field:SerializedName("genre_preferences")
    val genrePreferences: List<Genre?>? = null,

    @field:SerializedName("reading_cluster")
    val readingCluster: Int? = null,

    @field:SerializedName("previous_books")
    val previousBooks: List<Book?>? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
