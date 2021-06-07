package bangkit.capstone.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.BookSummaryApi
import bangkit.capstone.core.data.model.BookSummary
import bangkit.capstone.core.data.paging.BookSummaryPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookSummaryRepository @Inject constructor(
    private val api: BookSummaryApi
) {

    fun getAllWithinReadingCommitment(readingCommitmentId: String): Flow<PagingData<BookSummary>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { BookSummaryPagingSource(api, readingCommitmentId) }
        ).flow
    }

}