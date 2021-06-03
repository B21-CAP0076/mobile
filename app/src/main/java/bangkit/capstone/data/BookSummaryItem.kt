package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class BookSummaryItem(

    @field:SerializedName("summary")
	val summary: String? = null,

    @field:SerializedName("reading_commitment")
	val readingCommitment: ReadingCommitment? = null,

    @field:SerializedName("creation_date")
	val creationDate: String? = null,

    @field:SerializedName("id")
	val id: String? = null
)