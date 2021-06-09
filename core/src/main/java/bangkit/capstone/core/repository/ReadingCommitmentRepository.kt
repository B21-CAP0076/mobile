package bangkit.capstone.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import bangkit.capstone.core.data.api.ReadingCommitmentApi
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.ReadingCommitmentCreate
import bangkit.capstone.core.data.paging.ReadingCommitmentPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class ReadingCommitmentRepository @Inject constructor(
    private val api: ReadingCommitmentApi
) {
    fun getAllUserReadingCommitment(): Flow<PagingData<ReadingCommitment>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            pagingSourceFactory = {
                ReadingCommitmentPagingSource(
                    api,
                    ReadingCommitmentPagingSource.USER_READING_COMMITMENT
                )
            }
        ).flow
    }

    fun getAllUserPotentialMatch() : Flow<PagingData<ReadingCommitment>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
            pagingSourceFactory = {
                ReadingCommitmentPagingSource(
                    api,
                    ReadingCommitmentPagingSource.USER_POTENTIAL_MATCH
                )
            }
        ).flow
    }

    suspend fun getOwnReadingCommitmentWithinMatch(matchId: String) : Flow<ReadingCommitment> {
        return flowOf(api.getOwnReadingCommitmentWithinMatch(matchId))
    }

    suspend fun getPartnerReadingCommitmentWithinMatch(matchId: String) : Flow<ReadingCommitment> {
        return flowOf(api.getPartnerReadingCommitmentWithinMatch(matchId))
    }

    suspend fun create(readingCommitmentCreate: ReadingCommitmentCreate) : Flow<ReadingCommitment> {
        val readingCommitment = api.create(readingCommitmentCreate)
        return flowOf(readingCommitment)
    }

    suspend fun delete(id: String) : Flow<ReadingCommitment> {
        val readingCommitment = api.delete(id)
        return flowOf(readingCommitment)
    }

}