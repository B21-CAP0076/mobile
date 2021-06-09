package bangkit.capstone.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.GenreApi
import bangkit.capstone.core.data.model.Genre
import bangkit.capstone.core.data.paging.GenrePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class GenreRepository @Inject constructor(
    private val api: GenreApi
) {
    suspend fun getAll(name: String? = null): Flow<List<Genre>> {
        return flowOf(api.getAll(page = 1, name=name))
    }

    suspend fun get(id: String): Flow<Genre> {
        return flowOf(api.get(id))
    }


}