package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class Book(

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("genres")
    val genres: List<Genre?>? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("authors")
    val authors: List<AuthorsItem?>? = null
)
