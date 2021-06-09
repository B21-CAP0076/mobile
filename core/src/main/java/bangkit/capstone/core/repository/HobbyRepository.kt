package bangkit.capstone.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.HobbyApi
import bangkit.capstone.core.data.model.Hobby
import bangkit.capstone.core.data.paging.HobbyPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class HobbyRepository @Inject constructor(
    private val api: HobbyApi
) {
    suspend fun getAll(name: String? = null): Flow<List<Hobby>> {
        return flowOf(api.getAll(page = 1, name= name))
    }

    suspend fun get(id: String) : Flow<Hobby> {
        return flowOf(api.get(id))
    }

}