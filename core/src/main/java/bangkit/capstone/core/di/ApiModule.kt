package bangkit.capstone.core.di

import android.content.Context
import android.content.SharedPreferences
import bangkit.capstone.core.data.api.*
import bangkit.capstone.core.util.EnumConverterFactory
import bangkit.capstone.core.util.HeaderInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideUserApi(headerInterceptor: HeaderInterceptor): UserApi {
        return retrofitBuilder(UserApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideHobbyApi(headerInterceptor: HeaderInterceptor): HobbyApi {
        return retrofitBuilder(HobbyApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideBookApi(headerInterceptor: HeaderInterceptor): BookApi {
        return retrofitBuilder(BookApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideGenreApi(headerInterceptor: HeaderInterceptor): GenreApi {
        return retrofitBuilder(GenreApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideReadingCommitmentApi(headerInterceptor: HeaderInterceptor): ReadingCommitmentApi {
        return retrofitBuilder(ReadingCommitmentApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideBookSummaryApi(headerInterceptor: HeaderInterceptor): BookSummaryApi {
        return retrofitBuilder(BookSummaryApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideSwipeApi(headerInterceptor: HeaderInterceptor): MatchApi {
        return retrofitBuilder(MatchApi::class.java, headerInterceptor)
    }


    private fun <T> retrofitBuilder(apiClass: Class<T>, headerInterceptor: HeaderInterceptor): T {
        val baseUrl = "http://habitbangkit.tech:8080/"

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(headerInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .client(okHttpClient.build())
            .build()
            .create(apiClass)
    }


}