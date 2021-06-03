package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class Genre(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
