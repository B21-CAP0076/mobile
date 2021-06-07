package bangkit.capstone.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import bangkit.capstone.core.data.api.GenreApi
import bangkit.capstone.core.data.model.Genre
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GenrePagingSource @Inject constructor(
    private val api: GenreApi,
    private val name: String? = null
) : PagingSource<Int, Genre>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Genre> {
        val page = params.key ?: 1

        return try {
            val response = api.getAll(page = page, name = name)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isNullOrEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Genre>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}