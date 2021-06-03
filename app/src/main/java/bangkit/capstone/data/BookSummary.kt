package bangkit.capstone.data

import com.google.gson.annotations.SerializedName

data class BookSummary(

	@field:SerializedName("BookSummary")
	val bookSummary: List<BookSummaryItem> = listOf()
)


