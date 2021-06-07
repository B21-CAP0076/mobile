package bangkit.capstone.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.BookSummaryApi
import bangkit.capstone.core.data.model.BookSummary
import bangkit.capstone.core.data.model.BookSummaryCreate
import bangkit.capstone.core.data.model.BookSummaryUpdate
import bangkit.capstone.core.data.paging.BookSummaryPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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

    suspend fun create(
        readingCommitmentId: String,
        bookSummaryCreate: BookSummaryCreate
    ): Flow<BookSummary> {
        val bookSummary = api.create(readingCommitmentId, bookSummaryCreate)
        return flowOf(bookSummary)
    }

    suspend fun update(
        id: String,
        bookSummaryUpdate: BookSummaryUpdate
    ): Flow<BookSummary> {
        val bookSummary = api.update(id, bookSummaryUpdate)
        return flowOf(bookSummary)
    }

    suspend fun delete(id: String): Flow<BookSummary> {
        val bookSummary = api.delete(id)
        return flowOf(bookSummary)
    }


}