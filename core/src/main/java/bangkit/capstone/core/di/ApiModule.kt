package bangkit.capstone.core.di

import bangkit.capstone.core.data.api.*
import bangkit.capstone.core.util.EnumConverterFactory
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideUserApi(): UserApi {
        return retrofitBuilder(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHobbyApi(): HobbyApi {
        return retrofitBuilder(HobbyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookApi(): BookApi {
        return retrofitBuilder(BookApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGenreApi(): GenreApi {
        return retrofitBuilder(GenreApi::class.java)
    }

    @Singleton
    @Provides
    fun provideReadingCommitmentApi(): ReadingCommitmentApi {
        return retrofitBuilder(ReadingCommitmentApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookSummaryApi(): BookSummaryApi {
        return retrofitBuilder(BookSummaryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSwipeApi(): SwipeApi {
        return retrofitBuilder(SwipeApi::class.java)
    }


    private fun <T> retrofitBuilder(apiClass: Class<T>): T {
        val baseUrl = "http://habitbangkit.tech:8080/"

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .build()
            .create(apiClass)
    }


}