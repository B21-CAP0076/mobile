package bangkit.capstone.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.BookApi
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.paging.BookPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BookApi
) {

    fun getAll(title: String? = null): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { BookPagingSource(api, title) }
        ).flow
    }

    suspend fun get(id: String): Flow<Book> {
        return flowOf(api.get(id))
    }

}