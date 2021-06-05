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

class BookRepository @Inject constructor (
    private val api: BookApi
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getBooks(
        title: String? = null, author: String? = null, genre: String? = null
    ) : Flow<PagingData<Book>>{
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { BookPagingSource(
                api, title, author, genre
            )}
        ).flow
    }

    suspend fun getBook(id: String) : Flow<Book> {
        return flowOf(api.get(id))
    }


}