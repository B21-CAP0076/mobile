package bangkit.capstone.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import bangkit.capstone.core.data.api.ReadingCommitmentApi
import bangkit.capstone.core.data.model.ReadingCommitment
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ReadingCommitmentPagingSource @Inject constructor(
    private val api: ReadingCommitmentApi,
    private val ownerId: String? = null,
    private val partnerId: String? = null,
    private val ownerReadingCluster: Int? = null
) : PagingSource<Int, ReadingCommitment>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReadingCommitment> {
        val page = params.key ?: 1

        return try {
            val response = api.getAll(page, ownerId, partnerId, ownerReadingCluster)

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

    override fun getRefreshKey(state: PagingState<Int, ReadingCommitment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}