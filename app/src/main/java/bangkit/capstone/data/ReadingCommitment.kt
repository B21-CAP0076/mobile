package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class ReadingCommitment(

    @field:SerializedName("owner")
    val owner: User? = null,

    @field:SerializedName("end_date")
    val endDate: String? = null,

    @field:SerializedName("partner")
    val partner: User? = null,

    @field:SerializedName("book")
    val book: Book? = null,

    @field:SerializedName("creation_date")
    val creationDate: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)